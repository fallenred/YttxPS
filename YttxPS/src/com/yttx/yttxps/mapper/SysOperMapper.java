package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.SysOper;

public interface SysOperMapper {
	
	
	public SysOper findById(String  sysOperId);
	
	public int  insert(SysOper sysOper);
	
	public int  update(SysOper sysOper);

	public void updateOperStatByDepNo(SysOper oper);

}
