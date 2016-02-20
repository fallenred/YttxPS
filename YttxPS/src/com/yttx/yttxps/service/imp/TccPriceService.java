package com.yttx.yttxps.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceKey;
import com.yttx.yttxps.service.ITccPriceService;


@Service("tccPriceService")
public class TccPriceService implements ITccPriceService {
	
	@Autowired
	private TCCPriceMapper tccPriceMapper;

	@Override
	public TCCPrice selectTCCPrice(TCCPriceKey key) {
		// TODO Auto-generated method stub
		return tccPriceMapper.selectByPrimaryKey(key);
	}

}
