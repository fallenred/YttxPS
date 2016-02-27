package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TScenicGenMapper;
import com.yttx.yttxps.mapper.TgenMapper;
import com.yttx.yttxps.model.TScenicGen;
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
	
	@Autowired
	private TScenicGenMapper<TScenicGen> scenicGenMapper;

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
		List<TScenicGen> scenicGens = record.getScenicGens();
		int result = 0;
		
		result = genMapper.insert(record);
		for(TScenicGen scenicGen : scenicGens) {
			scenicGen.setFiIndex(scenicGenMapper.selectFiIndex());
			scenicGen.setFiGenindex(record.getFiIndex());
			result = scenicGenMapper.insert(scenicGen);
		}
		return result;
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
