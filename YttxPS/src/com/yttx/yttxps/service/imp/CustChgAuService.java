package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.CustChgAuMapper;
import com.yttx.yttxps.mapper.ScenicMapper;
import com.yttx.yttxps.model.CustChgAu;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.service.ICustChgAuService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IScenicService;


@Service("custChgAuService")
public class CustChgAuService implements ICustChgAuService {

	@Autowired
	private IPubService<CustChgAu> pubService;
	
	@Autowired
	private CustChgAuMapper<CustChgAu> custChgAuMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return custChgAuMapper.selectCountSelective(map);
	}

	@Override
	public List<CustChgAu> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, custChgAuMapper);
	}

	@Override
	public int insert(CustChgAu record) {
		return custChgAuMapper.insert(record);
	}

	@Override
	public int update(CustChgAu record) {
		return custChgAuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return custChgAuMapper.deleteByPrimaryKey(no);
	}

}
