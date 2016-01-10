package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Tgen;
import com.yttx.yttxps.model.TgenExample;


public interface IGenService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tgen> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Tgen gen);
	
	@Transactional
	int update(Tgen gen);
	
	@Transactional
	int delete(String no);

	List<Tgen> selectTgen(TgenExample example);
	
	int selectFiIndex();
}
