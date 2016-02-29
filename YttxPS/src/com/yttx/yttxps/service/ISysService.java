package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.model.vo.DeptAddRequest;
import com.yttx.yttxps.model.vo.SysOperSubRequest;


public interface ISysService {
	
	@Transactional(readOnly = true) 
	public SysOper findOperById(String sysOperId);
	
	@Transactional(readOnly = true) 
	public List<SysOperRight> findOperRight(String sysOperId);
	
	@Transactional
	public int updateSysOper(SysOper sysOper);
	
	@Transactional(readOnly = true) 
	public SysDep findDepByNo(long depNo);
	
	@Transactional(readOnly = true) 
	public List<SysDepRight> findDepRight(long depNo);
	
	
	@Transactional(readOnly = true) 
	public List<SysDep> findDepAll();
	
	
	/**
	 * 分页查询部门清单
	 */
	@Transactional(readOnly = true) 
	public List<SysDep> selectDepSelectivePage(Map<String, Object> map);

	/**
	 * 新增部门
	 */
	@Transactional
	public void addSysDep(DeptAddRequest req);
	
	/**
	 * 修改部门信息
	 */
	@Transactional
	public void updateSysDep(DeptAddRequest req);

	/**
	 * 注销该部门
	 */
	@Transactional
	public void cancelDeptByDepNo(Long depNo);

	/**
	 * 分页查询操作员清单
	 */
	@Transactional(readOnly = true) 
	public List<SysOper> selectOperSelectivePage(Map<String, Object> map);

	/**
	 * 查找特定状态的部门
	 */
	@Transactional(readOnly = true) 
	public List<SysDep> findDepsByStat(Long stat);

	
	/**
	 * 新增用户
	 */
	@Transactional
	public void addSysOper(SysOperSubRequest req);
	
	/**
	 * 更新用户信息
	 */
	@Transactional
	public void  updateSysOperbyOperId(SysOperSubRequest req,String operId,String currOperId);

	/**
	 * 删除用户
	 */
	@Transactional
	public void deleteOperByOperId(String sysOperId);

}
