package com.yttx.yttxps.service;

import java.util.List;

import com.yttx.yttxps.model.TOrderCusList;
import com.yttx.yttxps.model.TOrderCusListExample;



/**
 * 订单游客名单service
 * @author huangtao
 *
 */
public interface IOrderCusListService {

	List<TOrderCusList> selectByExample(TOrderCusListExample example);

	int insert(TOrderCusList record);
	
	int inser(List<TOrderCusList> list);
}
