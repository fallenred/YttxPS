package com.yttx.yttxps.service;


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
	void insert(TtransportArrange transportArrange);
	
	@Transactional
	void update(TtransportArrange record);
	
	@Transactional
	int deleteByNo(String no);
	
	List<TtransportArrangeKey> selectTtransportArrange(TtransportArrangeExample example);
	
	public int selectFsNo();
}
