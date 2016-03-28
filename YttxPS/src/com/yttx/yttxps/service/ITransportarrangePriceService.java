package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TransportarrangePrice;

public interface ITransportarrangePriceService {

	@Transactional(readOnly = true)
	List<TransportarrangePrice> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	void insertTransportarrangePrice(TCCPrice price);
	
	@Transactional
	void updateTransportarrangePrice(TCCPrice price);
	
	@Transactional
	void delTransportarrangePrice(TCCPrice price);
}
