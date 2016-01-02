package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.IBaseMapper;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;


public interface IPubService<T> {
	
	@Transactional(readOnly = true) 
	public List<RegionMap> findRegionByManageNo(String key);
	
	@Transactional(readOnly = true) 
	public List<T> doPage(Map<String, Object> map, IBaseMapper<T> mapper);

}
