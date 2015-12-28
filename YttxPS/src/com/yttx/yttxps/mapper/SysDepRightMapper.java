package com.yttx.yttxps.mapper;

import java.util.List;

import com.yttx.yttxps.model.SysDepRight;

public interface SysDepRightMapper {
	
	public List<SysDepRight> findByNo(long  depNo);
	
	
	public void insertbatch(List<SysDepRight> sysDepRights);
	

}
