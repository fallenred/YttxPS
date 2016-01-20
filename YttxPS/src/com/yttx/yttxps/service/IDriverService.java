package com.yttx.yttxps.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Driver;


public interface IDriverService {
	
	@Transactional(readOnly = true) 
	int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Driver> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	Driver selectDriverInfo(BigDecimal index);
	
	@Transactional
	int insert(Driver driver);
	
	@Transactional
	int update(Driver driver);
	
	@Transactional
	int delete(BigDecimal index);
	
	@Transactional(readOnly = true)
	BigDecimal selectSequence();
}
