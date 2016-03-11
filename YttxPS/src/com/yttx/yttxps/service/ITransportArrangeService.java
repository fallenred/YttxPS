package com.yttx.yttxps.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.TtransportArrangeKey;


public interface ITransportArrangeService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<TtransportArrange> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	void insert(TtransportArrange transportArrange) throws ParseException;
	
	@Transactional
	void update(TtransportArrange record) throws ParseException;
	
	@Transactional
	int deleteByNo(String no);
	
	List<TtransportArrangeKey> selectTtransportArrange(TtransportArrangeExample example);
	
	List<TtransportArrange> selectTtransportArrangeView(TtransportArrangeExample example);
	
	public int selectFsNo();
	
	public TtransportArrangeKey findUniqTtransportArrange(String fsNo);
}
