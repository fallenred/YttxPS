package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TguideMapper;
import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TguideExample;
import com.yttx.yttxps.service.IGuideService;
import com.yttx.yttxps.service.IPubService;


@Service("guideService")
public class GuideService implements IGuideService {

	@Autowired
	private IPubService<Tguide> pubService;
	
	@Autowired
	private TguideMapper<Tguide> guideMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return guideMapper.selectCountSelective(map);
	}

	@Override
	public List<Tguide> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, guideMapper);
	}
	
	@Override
	public List<Tguide> selectTguide(TguideExample example) {
		return guideMapper.selectByExample(example);
	}
	
	@Override
	public Tguide findTguide(String fsNo) {
		return guideMapper.selectByPrimaryKey(fsNo);
	}

	@Override
	public int insert(Tguide record) {
		return guideMapper.insert(record);
	}

	@Override
	public int update(Tguide record) {
		return guideMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return guideMapper.deleteByPrimaryKey(no);
	}





}
