package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;


public interface IOrderlistService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TOrderlistWithBLOBs> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(TOrderlistWithBLOBs orderlist);
	
	@Transactional
	int update(TOrderlistWithBLOBs orderlist);
	
	@Transactional
	int delete(String no);

	List<TOrderlistWithBLOBs> selectTOrderlist(TOrderlistExample example);
	
}
