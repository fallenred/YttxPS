package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TEntertainment;
import com.yttx.yttxps.model.TEntertainmentExample;

public interface IEntertainmentService {

	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TEntertainment> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	public int selectCountEntertainmentPrice(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TEntertainment> selectEntertainmentPricePage(Map<String, Object> map);
	
	@Transactional
	void insert(TEntertainment record);
	
	@Transactional
	void insertEntertainmentPrice(TEntertainment record);
	
	@Transactional
	void update(TEntertainment record);
	
	@Transactional
	void updateEntertainmentPrice(TEntertainment record);
	
	@Transactional
	void delete(String no);
	
	@Transactional
	void deleteEntertainmentPrice(TCCPrice price);

	@Transactional
	List<TEntertainment> selectEntertainment(TEntertainmentExample example);
	
	@Transactional
	List<TEntertainment> selectEntertainmentDynamic(Map<String, Object> map);
}
