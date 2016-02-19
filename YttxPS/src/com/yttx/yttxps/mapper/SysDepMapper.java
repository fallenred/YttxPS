package com.yttx.yttxps.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.SysDep;

public interface SysDepMapper<T> extends IBaseMapper<T> {
	
	public List<SysDep> findAll();
	
	/**
	 * 查找特定状态的部门
	 */
	public List<SysDep> findDepsByStat(@Param("stat") Long stat);
	
	public SysDep findByNo(long  depno);
	
	public SysDep findByDepName(String depName);
	
	public void  insert(SysDep sysDep);
	
	public void update(SysDep sysDep);
	
}
