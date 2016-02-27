package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TScenicGenMapper;
import com.yttx.yttxps.model.TScenicGen;
import com.yttx.yttxps.model.TScenicGenExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IScenicGenService;


@Service("scenicGenService")
public class ScenicGenService implements IScenicGenService {

	@Autowired
	private IPubService<TScenicGen> pubService;
	
	@Autowired
	private TScenicGenMapper<TScenicGen> scenicGenMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return scenicGenMapper.selectCountSelective(map);
	}

	@Override
	public List<TScenicGen> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, scenicGenMapper);
	}
	
	@Override
	public List<TScenicGen> selectScenicGen(TScenicGenExample example) {
		return scenicGenMapper.selectByExample(example);
	}

	@Override
	public int insert(TScenicGen record) {
		return scenicGenMapper.insert(record);
	}

	@Override
	public int update(TScenicGen record) {
		return scenicGenMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(BigDecimal fiIndex) {
		return scenicGenMapper.deleteByPrimaryKey(fiIndex);
	}

}
