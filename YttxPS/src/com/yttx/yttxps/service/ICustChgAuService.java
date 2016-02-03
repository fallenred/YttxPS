package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.CustChgAu;


public interface ICustChgAuService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<CustChgAu> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(CustChgAu scenic);
	
	@Transactional
	int update(CustChgAu scenic);
	
	@Transactional
	int delete(String no);
}
