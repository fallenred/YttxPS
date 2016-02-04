package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCloselistMapper;
import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.TCloselistExample;
import com.yttx.yttxps.service.ICloselistService;
import com.yttx.yttxps.service.IPubService;


@Service("closelistService")
public class CloselistService implements ICloselistService {

	@Autowired
	private IPubService<TCloselist> pubService;
	
	@Autowired
	private TCloselistMapper<TCloselist> closelistMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return closelistMapper.selectCountSelective(map);
	}

	@Override
	public List<TCloselist> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, closelistMapper);
	}

	@Override
	public int insert(TCloselist record) {
		return closelistMapper.insert(record);
	}

	@Override
	public int update(TCloselist record) {
		return closelistMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return closelistMapper.deleteByPrimaryKey(no);
	}

	@Override
	public List<TCloselist> selectTOrderlist(TCloselistExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return closelistMapper.selectByExampleWithBLOBs(example);
	}
	
}
