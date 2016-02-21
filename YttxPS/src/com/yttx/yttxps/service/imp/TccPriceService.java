package com.yttx.yttxps.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceKey;
import com.yttx.yttxps.service.ITccPriceService;


@Service("tccPriceService")
public class TccPriceService implements ITccPriceService {
	
	@Autowired
	private TCCPriceMapper tccPriceMapper;

	@Override
	public TCCPrice selectTCCPriceByKey(TCCPriceKey key) {
		// TODO Auto-generated method stub
		return tccPriceMapper.selectByPrimaryKey(key);
	}

	@Override
	public List<TCCPrice> selectTCCPrice(TCCPriceExample example) {
		// TODO Auto-generated method stub
		return tccPriceMapper.selectByExample(example);
	}

}
