package com.yttx.yttxps.mapper;

import java.util.List;

import com.yttx.yttxps.model.SysOperRight;

public interface SysOperRightMapper {
	
	
	public List<SysOperRight> findById(String  sysOperId);
	
	
	public void insertbatch(List<SysOperRight> sysOperRights);
	
	

}
