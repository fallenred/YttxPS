package com.yttx.yttxps.model;

import com.yttx.comm.DateUtil;

/**
 * 类描述：用来封装页面过滤订单的条件
 * @author sunchao
 * @date 2016年2月23日 下午4:20:46
 */
public class OrderFilters {
	//订单Id
	private String orderId;
	
	//订单名称
	private String oderName;
	
	//计调ID
	private String OperId;
	
	//创建时间
	private String createDateRange;
	
	private String startDate;
	
	private String endDate;
	
	//线路类型
	private String routeType;
	
	//旅游团性质
	private String itemProp;
	
	private Integer stat;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOderName() {
		return oderName;
	}

	public void setOderName(String oderName) {
		this.oderName = oderName;
	}

	public String getOperId() {
		return OperId;
	}

	public void setOperId(String operId) {
		OperId = operId;
	}

	public String getCreateDateRange() {
		return createDateRange;
	}

	public void setCreateDateRange(String createDateRange) {
		this.createDateRange = createDateRange;
	}


	public String getStartDate(){
		return DateUtil.getDateStrFromDateRange(createDateRange,0);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return DateUtil.getDateStrFromDateRange(createDateRange,1);
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getItemProp() {
		return itemProp;
	}

	public void setItemProp(String itemProp) {
		this.itemProp = itemProp;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
}
