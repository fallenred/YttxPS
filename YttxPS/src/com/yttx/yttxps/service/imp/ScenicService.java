package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.ScenicMapper;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IScenicService;


@Service("scenicService")
public class ScenicService implements IScenicService {

	@Autowired
	private IPubService<Scenic> pubService;
	
	@Autowired
	private ScenicMapper<Scenic> scenicMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return scenicMapper.selectCountSelective(map);
	}

	@Override
	public List<Scenic> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, scenicMapper);
	}

	@Override
	public int insert(Scenic record) {
		return scenicMapper.insert(record);
	}

	@Override
	public int update(Scenic record) {
		return scenicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return scenicMapper.deleteByPrimaryKey(no);
	}





}
