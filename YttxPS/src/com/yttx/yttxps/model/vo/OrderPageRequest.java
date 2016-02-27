package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.OrderFilters;

/**
 * 类描述：封装页面请求订单列表的数据
 * @author sunchao
 * @date 2016年2月23日 下午4:14:21
 */
public class OrderPageRequest extends JqGridRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private OrderFilters filters = new OrderFilters();
	
	
	public OrderFilters getFilters() {
		return filters;
	}

	public void setFilters(OrderFilters filters) {
		this.filters = filters;
	}

	/**
	 * 将过滤条件放入map中
	 */
	public void copyOrderFilters(Map<String,Object> map){
		if(filters!=null){
			map.put("orderId",filters.getOrderId());
			map.put("orderName",filters.getOderName());
			map.put("operId",filters.getOperId());
			map.put("startDate",filters.getStartDate());
			map.put("endDate",filters.getEndDate());
			map.put("routeType",filters.getRouteType());
			map.put("itemProp",filters.getItemProp());	
			map.put("stat",filters.getStat());	
		}
	}

}
