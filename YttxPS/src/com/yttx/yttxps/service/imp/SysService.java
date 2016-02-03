package com.yttx.yttxps.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.mapper.SysDepMapper;
import com.yttx.yttxps.mapper.SysDepRightMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.SysOperRightMapper;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.model.vo.DeptAddRequest;
import com.yttx.yttxps.model.vo.SysOperSubRequest;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ISysService;


@Service("sysService")
public class SysService implements ISysService {
	
	@Autowired
	private IPubService<SysDep> pubService;
	
	@Autowired
	private IPubService<SysOper> pubOperService;
	
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
	
	
	/**
	 * 分页查询部门清单
	 */
	@Override
	public List<SysDep> selectDepSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, sysDepMapper);
	}

	/**
	 * 新增部门
	 * @throws Exception 
	 */
	@Override
	public void addSysDep(DeptAddRequest req){
		SysDep sysDep=req.getSysDep();
		
		String depName = sysDep.getDepName();
		SysDep fSysDep=sysDepMapper.findByDepName(depName);
		if(null!=fSysDep){
			throw new RuntimeException("该部门已存在");
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
		oper.setStat(2L);
		sysOperMapper.updateOperStatByDepNo(oper);	
	}

	/**
	 * 分页查询操作员清单
	 */
	@Override
	public List<SysOper> selectOperSelectivePage(Map<String, Object> map) {
		return pubOperService.doPage(map,sysOperMapper);
	}

	/**
	 * 查找特定状态的部门
	 */
	@Override
	public List<SysDep> findDepsByStat(Long stat) {
		return sysDepMapper.findDepsByStat(stat);
	}

	/**
	 * 新增用户
	 */
	@Override
	public void addSysOper(SysOperSubRequest req){
		//向用户表中插入数据
		SysOper oper = req.getSysOper();
		String operID= oper.getSysOperId();
		SysOper exitOper = sysOperMapper.findById(operID);
		if(null!=exitOper){
			throw new RuntimeException("该用户编号已存在");
		}
		
		sysOperMapper.insert(oper);
		
		//向用户权限表中插入数据
		List<String> rights = req.getRights();
		if(rights!=null&&rights.size()>0){
			List<SysOperRight> sysOperRights = new ArrayList<SysOperRight>();
			for(String right:rights){
				SysOperRight operRight = new SysOperRight();
				operRight.setSysOperId(oper.getSysOperId());
				operRight.setRight(right);
				sysOperRights.add(operRight);
			}
			sysOperRightMapper.insertBatch(sysOperRights);
		}
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public void updateSysOperbyOperId(SysOperSubRequest req,String operId) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		//更新用户表中的数据
		map.put("oldOperId", operId);
		SysOper oper = req.getSysOper();
		
		String newOperId = oper.getSysOperId();
		if(null!=newOperId && newOperId != "" && (!newOperId.equalsIgnoreCase(operId))){
			SysOper isExitOper = sysOperMapper.findById(newOperId);
			if(isExitOper != null){
				throw new RuntimeException("该用户编号已经存在");
			}
		}
		
		map.put("operId", oper.getSysOperId());
		map.put("operName", oper.getSysOperName());
		map.put("depNo", oper.getDepNo());
		map.put("adminType", oper.getAdminType());
		map.put("pwd", oper.getSysOperPwd());
		map.put("pwdStat", oper.getPwdStat());
		map.put("stat", oper.getStat());
		sysOperMapper.updateOperByOperId(map);
		
		
		//删除权限表中的数据
		sysOperRightMapper.deleteByOperId(operId);
		//更新权限表中的数据
		List<String> rightIdList = req.getRights();
		if(rightIdList !=null && rightIdList.size()>0){
			List<SysOperRight> operRights = new ArrayList<SysOperRight>();
			for(String rightId:rightIdList){
				SysOperRight operRight = new SysOperRight();
				operRight.setSysOperId(oper.getSysOperId());
				operRight.setRight(rightId);
				operRights.add(operRight);
			}
			sysOperRightMapper.insertBatch(operRights);
		}	
	}
	
	/**
	 * 删除用户
	 */
	@Override
	public void deleteOperByOperId(String sysOperId) {
		//删除用户权限
		sysOperRightMapper.deleteByOperId(sysOperId);
		
		//删除用户
		sysOperMapper.deleteOperByOperId(sysOperId);
		
	}
}
