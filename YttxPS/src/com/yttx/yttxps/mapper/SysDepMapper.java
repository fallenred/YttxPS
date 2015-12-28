package com.yttx.yttxps.mapper;

import java.util.List;

import com.yttx.yttxps.model.SysDep;


public interface SysDepMapper {
	
	public List<SysDep> findAll();
	
	public SysDep findByNo(long  depno);
	
	public void  insert(SysDep sysDep);
	
}
