package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TOrderCustomExample;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;


public interface IOrderCustomService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TOrderCustomWithBLOBs> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(TOrderCustomWithBLOBs orderCustom);
	
	@Transactional
	int update(TOrderCustomWithBLOBs orderCustom);
	
	@Transactional
	int delete(String no);

	List<TOrderCustomWithBLOBs> selectTOrderCustom(TOrderCustomExample example);
	
}
