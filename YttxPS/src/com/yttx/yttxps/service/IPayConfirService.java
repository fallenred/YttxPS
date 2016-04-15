package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;

/**
 * 类描述：支付确认service 
 */
public interface IPayConfirService {

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
	 * 通过订单Id找到结算单
	 */
	@Transactional(readOnly = true) 
	FStatement findFStatByOrderId(String orderId);

	/**
	 * 订单支付确认
	 * @param order 订单信息
	 * @param operId 操作员id
	 */
	void orderConfir(TOrderlist order, String operId);

	/**
	 * 结算单支付确认
	 * @param statement 结算单信息
	 * @param operId 操作员id
	 */
	void statementConfir(FStatement statement, String operId);
		
}
