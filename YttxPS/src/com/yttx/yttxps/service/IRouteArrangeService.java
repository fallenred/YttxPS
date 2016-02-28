package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeWithBLOBs;


public interface IRouteArrangeService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TRouteArrange> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(TRouteArrangeWithBLOBs routeArrange);
	
	@Transactional
	int update(TRouteArrangeWithBLOBs routeArrange);
	
	@Transactional
	void delete(String no);

	List<TRouteArrange> selectTRouteArrange(TRouteArrangeExample example);
	
	@Transactional(rollbackFor=Exception.class)
	public void insertRouteCC(TRouteArrangeWithBLOBs record);
	
}
