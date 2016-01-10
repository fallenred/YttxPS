package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Ttransport;
import com.yttx.yttxps.model.TtransportExample;


public interface ITransportService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Ttransport> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Ttransport transport);
	
	@Transactional
	int update(Ttransport transport);
	
	@Transactional
	int delete(String no);
	
	List<Ttransport> selectTtransport(TtransportExample example);
}
