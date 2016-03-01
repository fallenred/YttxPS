package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;

/**
 * 类描述：结算单模块的service 
 */
public interface IClearOrderService {

	/**
	 * 分页查询简单订单列表
	 */
	@Transactional(readOnly = true) 
	List<SimpleOrder> selectSelectivePage(Map<String, Object> map);

	/**
	 * 通过订单唯一索引（orderId）找到订单的详细信息
	 */
	@Transactional(readOnly = true) 
	DetailOrder findOrderDetail(String orderId);

	/**
	 * 通过routeId找到routeName
	 */
	@Transactional(readOnly = true) 
	String findRouteName(String routeId);

	/**
	 * 通过订单Id找到结算单
	 */
	@Transactional(readOnly = true) 
	FStatement findFStatByOrderId(String orderId);

	/**
	 * 增加一条结算单
	 */
	@Transactional 
	String addFStatement(FStatement fStatement);
		
}
