package com.yttx.yttxps.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Pic;


public interface IPicService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Pic> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Pic pic);
	
	@Transactional
	int delete(BigDecimal index);
	
	@Transactional(readOnly = true)
	BigDecimal selectSequence();
}
