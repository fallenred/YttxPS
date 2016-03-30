package com.yttx.yttxps.mapper;

import java.util.Map;

import com.yttx.yttxps.model.TransportarrangePrice;

public interface TransportarrangePriceMapper<T> extends IBaseMapper<T> {
	TransportarrangePrice selectTarrangePrice(Map<String, Object> map);
}
