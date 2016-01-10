package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;


public interface ITicketService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tticket> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Tticket ticket);
	
	@Transactional
	int update(Tticket ticket);
	
	@Transactional
	int delete(String no);

	int selectMaxSeq(TticketExample example);
	
	List<Tticket> selectTticket(TticketExample example);
	
	int selectFsNo();
}
