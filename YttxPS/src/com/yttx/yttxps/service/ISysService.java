package com.yttx.yttxps.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;


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

}
