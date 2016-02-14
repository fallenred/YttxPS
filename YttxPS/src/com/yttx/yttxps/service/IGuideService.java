package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TguideExample;


public interface IGuideService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tguide> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tguide> selectTguide(TguideExample example);
	
	@Transactional
	int insert(Tguide guide);
	
	@Transactional
	int update(Tguide guide);
	
	@Transactional
	int delete(String no);
}
