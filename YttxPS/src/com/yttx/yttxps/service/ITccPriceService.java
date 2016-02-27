package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceKey;


public interface ITccPriceService {
	
	TCCPrice selectTCCPriceByKey(TCCPriceKey key);
	
	List<TCCPrice> selectTCCPrice(TCCPriceExample example);
	
	List<TCCPrice> selectTCCPrice(Map<String, Object> parameter);
}
