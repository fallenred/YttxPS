package com.yttx.yttxps.model;

import com.yttx.comm.DateUtil;

/**
 * 类描述：封装请求结算单的过滤条件
 * @author sunchao
 * @date 2016年2月26日 上午10:50:20
 */
public class FStatementFilters {
	//结算单ID
	private String statementId;
	
	//订单Id
	private String orderId;
		
	//订单名称
	private String userId;
		
	//计调ID
	private String operId;
		
	//创建时间
	private String createDateRange;
	
	private String startDate;
	
	private String endDate;
		
	private Long stat=0L;

	//结算单名称
	private String name;

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getCreateDateRange() {
		return createDateRange;
	}

	public void setCreateDateRange(String createDateRange) {
		this.createDateRange = createDateRange;
	}

	public String getStartDate() {
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

	public Long getStat() {
		return stat;
	}

	public void setStat(Long stat) {
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
