package com.yttx.yttxps.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TScenicGen;
import com.yttx.yttxps.model.TScenicGenExample;


public interface IScenicGenService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TScenicGen> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	List<TScenicGen> selectScenicGen(TScenicGenExample example);
	
	@Transactional
	int insert(TScenicGen scenic);
	
	@Transactional
	int update(TScenicGen scenic);
	
	@Transactional
	int delete(BigDecimal fiIndex);
}
