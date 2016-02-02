package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;


public interface ITicketService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tticket> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	public int selectCountTicketPrice(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Tticket> selectTicketPricePage(Map<String, Object> map);
	
	@Transactional
	void insert(Tticket ticket);
	
	@Transactional
	void insertTicketPrice(Tticket ticket);
	
	@Transactional
	void update(Tticket ticket);
	
	@Transactional
	void updateTicketPrice(Tticket ticket);
	
	@Transactional
	void delete(String no);
	
	@Transactional
	void deleteTicketPrice(TCCPrice price);

	List<Tticket> selectTticket(TticketExample example);
	
}
