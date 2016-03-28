package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TransportarrangePriceMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TransportarrangePrice;
import com.yttx.yttxps.service.ITransportarrangePriceService;

@Service("transportarrangePriceService")
public class TransportarrangePriceService implements ITransportarrangePriceService {

	@Autowired
	private TransportarrangePriceMapper<TransportarrangePrice> transportarrangePriceMapper;
	
	@Autowired
	private TCCPriceMapper priceMapper;

	@Override
	public List<TransportarrangePrice> selectSelectivePage(Map<String, Object> map) {
		return transportarrangePriceMapper.selectSelectivePage(map);
	}

	@Override
	public void insertTransportarrangePrice(TCCPrice price) {
		price.setFsCcno("000023");   //车辆消费
		price.setFsRestype("cx");   //车型线路
		priceMapper.insertPrice(price);
	}

	@Override
	public void updateTransportarrangePrice(TCCPrice price) {
		priceMapper.updateByPrimaryKey(price);
	}

	@Override
	public void delTransportarrangePrice(TCCPrice price) {
		priceMapper.deleteByPrimaryKey(price);
	}

}
