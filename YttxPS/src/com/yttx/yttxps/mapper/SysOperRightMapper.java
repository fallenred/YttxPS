package com.yttx.yttxps.mapper;

import java.util.List;

import com.yttx.yttxps.model.SysOperRight;

public interface SysOperRightMapper {
	
	public List<SysOperRight> findById(String  sysOperId);
	
	public void insertBatch(List<SysOperRight> sysOperRights);
	
	public boolean deleteByOperId(String sysOperId);

	public void deleteFromDepRight(String operId);
	
}
