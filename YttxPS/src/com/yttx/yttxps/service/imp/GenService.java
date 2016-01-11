package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TgenMapper;
import com.yttx.yttxps.model.Tgen;
import com.yttx.yttxps.model.TgenExample;
import com.yttx.yttxps.service.IGenService;
import com.yttx.yttxps.service.IPubService;


@Service("genService")
public class GenService implements IGenService {

	@Autowired
	private IPubService<Tgen> pubService;
	
	@Autowired
	private TgenMapper<Tgen> genMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return genMapper.selectCountSelective(map);
	}

	@Override
	public List<Tgen> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, genMapper);
	}

	@Override
	public int insert(Tgen record) {
		return genMapper.insert(record);
	}

	@Override
	public int update(Tgen record) {
		return genMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return genMapper.deleteByPrimaryKey(new BigDecimal(no));
	}

	@Override
	public List<Tgen> selectTgen(TgenExample example) {
		// TODO Auto-generated method stub
		return genMapper.selectByExample(example);
	}

	@Override
	public int selectFiIndex() {
		// TODO Auto-generated method stub
		return genMapper.selectFiIndex();
	}
}
