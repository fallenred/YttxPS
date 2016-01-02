package com.yttx.yttxps.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.ScenicMapper;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.service.IScenicService;


@Service("scenicService")
public class ScenicService implements IScenicService {

	
	@Autowired
	private ScenicMapper scenicMapper;

	@Override
	public int selectCountSelective(Scenic scenic) {
		return scenicMapper.selectCountSelective(scenic);
	}

}
