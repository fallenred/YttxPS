package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.model.vo.DeptAddRequest;


public interface ISysService {
	
	@Transactional(readOnly = true) 
	public SysOper findOperById(String sysOperId);
	
	@Transactional(readOnly = true) 
	public List<SysOperRight> findOperRight(String sysOperId);
	
	public int updateSysOper(SysOper sysOper);
	
	@Transactional(readOnly = true) 
	public SysDep findDepByNo(long depNo);
	
	@Transactional(readOnly = true) 
	public List<SysDepRight> findDepRight(long depNo);
	
	
	@Transactional(readOnly = true) 
	public List<SysDep> findDepAll();

	@Transactional(readOnly = true) 
	public List<SysDep> selectDepSelectivePage(Map<String, Object> map);

	/**
	 * 新增部门
	 * @throws Exception 
	 */
	@Transactional
	public void addSysDep(DeptAddRequest req) throws Exception;
	
	/**
	 * 修改部门信息
	 */
	@Transactional
	public void updateSysDep(DeptAddRequest req);

	/**
	 * 注销该部门
	 */
	public void cancelDeptByDepNo(Long depNo);

}
