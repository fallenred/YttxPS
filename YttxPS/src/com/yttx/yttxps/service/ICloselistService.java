package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.TCloselistExample;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;


public interface ICloselistService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TCloselist> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	public Boolean creatCloseList(TOrderlistWithBLOBs orderList, List<TOrderCustomWithBLOBs> orderCustomList) throws Exception;
	
	@Transactional
	int insert(TCloselist orderlist);
	
	@Transactional
	int update(TCloselist orderlist);
	
	@Transactional
	int delete(String no);

	List<TCloselist> selectTOrderlist(TCloselistExample example);
	
}
