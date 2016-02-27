package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.corder.DetailOrder;

/**
 * 接口描述：结算单模块的mapper
 * @author sunchao
 * @date 2016年2月23日 下午5:26:28
 */
public interface ClearOrderMapper<T> extends IBaseMapper<T>{

	/**
	 * 通过orderId找到订单的详细信息
	 */
	DetailOrder findOrderDetail(String orderId);

	/**
	 * 通过routeId找到routeName
	 */
	String findRouteNameById(String routeId);

	/**
	 * 更新该订单的状态
	 */
	boolean updateStatById(String orderId);
	
}
