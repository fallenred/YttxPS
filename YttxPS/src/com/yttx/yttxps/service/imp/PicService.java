package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.PicMapper;
import com.yttx.yttxps.model.Pic;
import com.yttx.yttxps.service.IPicService;


@Service("picService")
public class PicService implements IPicService {

	@Autowired
	private PubService<Pic> pubService;
	
	@Autowired
	private PicMapper<Pic> picMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return picMapper.selectCountSelective(map);
	}

	@Override
	public List<Pic> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, picMapper);
	}

	@Override
	public int insert(Pic record) {
		return picMapper.insert(record);
	}

	@Override
	public int delete(BigDecimal index) {
		return picMapper.deleteByPrimaryKey(index);
	}





}
