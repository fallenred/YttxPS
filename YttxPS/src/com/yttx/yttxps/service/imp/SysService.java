package com.yttx.yttxps.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.SysDepMapper;
import com.yttx.yttxps.mapper.SysDepRightMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.SysOperRightMapper;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.model.vo.DeptAddRequest;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ISysService;


@Service("sysService")
public class SysService implements ISysService {
	
	@Autowired
	private IPubService<SysDep> pubService;
	
	@Autowired
	private SysOperMapper sysOperMapper;
	
	@Autowired
	private SysOperRightMapper sysOperRightMapper;
	
	@Autowired
	private SysDepMapper<SysDep> sysDepMapper;
	
	
	@Autowired
	private SysDepRightMapper sysDepRightMapper;

	@Override
	public SysOper findOperById(String sysOperId) {
		return sysOperMapper.findById(sysOperId);
	}
	
	@Override
	public List<SysOperRight> findOperRight(String sysOperId ){
		return sysOperRightMapper.findById(sysOperId);
	}

	@Override
	public SysDep findDepByNo(long depNo) {
		return sysDepMapper.findByNo(depNo);
	}

	@Override
	public List<SysDepRight> findDepRight(long depNo) {
		return sysDepRightMapper.findByNo(depNo);
	}

	@Override
	public List<SysDep> findDepAll() {
		return sysDepMapper.findAll();
	}

	@Override
	public int updateSysOper(SysOper sysOper) {
		return sysOperMapper.update(sysOper);
	}

	@Override
	public List<SysDep> selectDepSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, sysDepMapper);
	}

	/**
	 * 新增部门
	 * @throws Exception 
	 */
	@Override
	public void addSysDep(DeptAddRequest req) throws Exception {
		SysDep sysDep=req.getSysDep();
		
		String depName = sysDep.getDepName();
		SysDep fSysDep=sysDepMapper.findByDepName(depName);
		if(null!=fSysDep){
			throw new Exception("该部门已存在");
		}
		sysDepMapper.insert(sysDep);
		
		long depNo=sysDep.getDepNo();
		
		List<String> rights=req.getRights();
		if(null != rights &&rights.size()>0){
			List<SysDepRight> depRights = new ArrayList<SysDepRight>();
			for(String rightId : rights){
				SysDepRight depRight = new SysDepRight();
				depRight.setDepNo(depNo);
				depRight.setRight(rightId);
				depRights.add(depRight);
			}
			sysDepRightMapper.insertBatch(depRights);
		}	
	}

	/**
	 * 修改部门信息
	 */
	@Override
	public void updateSysDep(DeptAddRequest req) {
		SysDep sysDep=req.getSysDep();
		//更新部门数据
		sysDepMapper.update(sysDep);
		
		//清空该部门的权限
		sysDepRightMapper.deleteRightsByDepNo(sysDep.getDepNo());
		
		//重新添加该部门的权限
		List<String> rightStrs = req.getRights();
		if(rightStrs != null && rightStrs.size()>0){
			List<SysDepRight> list = new ArrayList<SysDepRight>();
			for(String right : rightStrs){
				SysDepRight depRight = new SysDepRight();
				depRight.setDepNo(sysDep.getDepNo());
				depRight.setRight(right);
				list.add(depRight);
			}
			sysDepRightMapper.insertBatch(list);
		}
	}

	/**
	 * 注销部门
	 */
	@Override
	public void cancelDeptByDepNo(Long depNo){
		SysDep sysDep = new SysDep();
		sysDep.setDepNo(depNo);
		sysDep.setStat(2L);
		
		//将部门的状态置为注销状态
		sysDepMapper.update(sysDep);
		
		//将该部门下的操作员的状态置为注销状态
		SysOper oper = new SysOper();
		oper.setDepNo(depNo);
		oper.setStat(0L);
		sysOperMapper.updateOperStatByDepNo(oper);	
	}
}
