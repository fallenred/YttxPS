package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.TshopMapper;
import com.yttx.yttxps.model.Tshop;
import com.yttx.yttxps.model.TshopExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IShopService;


@Service("shopService")
public class ShopService implements IShopService {

	@Autowired
	private IPubService<Tshop> pubService;
	
	@Autowired
	private TshopMapper<Tshop> shopMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return shopMapper.selectCountSelective(map);
	}

	@Transactional(readOnly = true) 
	public List<Tshop> selectTshop(TshopExample example){
		return shopMapper.selectByExample(example);
	}
	
	@Override
	public List<Tshop> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, shopMapper);
	}

	@Override
	public int insert(Tshop record) {
		return shopMapper.insert(record);
	}

	@Override
	public int update(Tshop record) {
		return shopMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return shopMapper.deleteByPrimaryKey(no);
	}
}
