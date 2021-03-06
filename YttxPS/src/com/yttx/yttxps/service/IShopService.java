package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Tshop;
import com.yttx.yttxps.model.TshopExample;


public interface IShopService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tshop> selectTshop(TshopExample example);
	
	@Transactional(readOnly = true) 
	List<Tshop> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Tshop shop);
	
	@Transactional
	int update(Tshop shop);
	
	@Transactional
	int delete(String no);
	
	List<Tshop> selectTshopByMap(Map<String, Object> map);
}
