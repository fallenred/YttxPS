package com.yttx.yttxps.service;


import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceKey;


public interface ITccPriceService {
	TCCPrice selectTCCPrice(TCCPriceKey key);
}
