package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.AccomadationMapper;
import com.yttx.yttxps.model.Accomadation;
import com.yttx.yttxps.service.IAccomadationService;
import com.yttx.yttxps.service.IPubService;

/**
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 * 
 */
@Service("accomadationService")
public class AccomadationService implements IAccomadationService {

	@Autowired
	private IPubService<Accomadation> pubService;
	
	@Autowired
	private AccomadationMapper<Accomadation> accomadationMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return accomadationMapper.selectCountSelective(map);
	}

	@Override
	public List<Accomadation> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, accomadationMapper);
	}

	@Override
	public int insert(Accomadation record) {
		System.out.println(record.getDesc());
		return accomadationMapper.insert(record);
	}

	@Override
	public int update(Accomadation record) {
		return accomadationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return accomadationMapper.deleteByPrimaryKey(no);
	}

	@Override
	public Accomadation selectAccomadationInfo(String no) {
		// TODO Auto-generated method stub
		return null;
	}





}
