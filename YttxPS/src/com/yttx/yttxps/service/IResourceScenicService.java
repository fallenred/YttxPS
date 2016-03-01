package com.yttx.yttxps.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TResourceScenic;
import com.yttx.yttxps.model.TResourceScenicExample;


public interface IResourceScenicService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TResourceScenic> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(TResourceScenic resourceScenic);
	
	@Transactional
	int update(TResourceScenic resourceScenic);
	
	@Transactional
	int delete(BigDecimal index);
	
	@Transactional(readOnly = true) 
	List<TResourceScenic> selectTResourceScenic(TResourceScenicExample example);
	
}
