package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.AccomadationMapper;
import com.yttx.yttxps.mapper.TOrderCusListMapper;
import com.yttx.yttxps.model.Accomadation;
import com.yttx.yttxps.model.TOrderCusList;
import com.yttx.yttxps.model.TOrderCusListExample;
import com.yttx.yttxps.model.TOrderCusListExample.Criteria;
import com.yttx.yttxps.service.IAccomadationService;
import com.yttx.yttxps.service.IOrderCusListService;
import com.yttx.yttxps.service.IPubService;

/**
 * 
 * 订单游客名单service
 * @author huangtao
 *
 */
@Service("orderCusListService")
public class OrderCusListService implements IOrderCusListService {

	@Autowired
	private TOrderCusListMapper orderCusListMapper;

	@Override
	public List<TOrderCusList> selectByExample(TOrderCusListExample example) {
		return orderCusListMapper.selectByExample(example);
	}

	@Override
	public int insert(TOrderCusList record) {
		return orderCusListMapper.insert(record);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int inser(List<TOrderCusList> list){
		// TODO Auto-generated method stub
		TOrderCusListExample example = new TOrderCusListExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsOrderIdEqualTo(list.get(0).getFsOrderId());
		//先删除已有的游客名单
		orderCusListMapper.deleteByExample(example);
		for (TOrderCusList orderCusList : list){
			orderCusListMapper.insert(orderCusList);
		}
		return list.size();
	}

}
