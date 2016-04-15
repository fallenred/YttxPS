package com.yttx.yttxps.model.corder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类描述：结算单对应的实体类
 *@author sunchao
 *@date 2016年2月25日 下午1:29:21
 */
public class FStatement {
	//结算
	private String statmentId;
	
	//线路ID
	private String routeID;
	
	//线路名称
	private String routeName;
	
	//结算单名称
	private String statmentName;
	
	//订单ID
	private String orderId;
	
	//用户ID
	private String userID;
	
	//用户子ID
	private String userSubID;
	
	//计调ID
	private String operId;
	
	//创建时间
	private Date creatDate;
	
	private String creatDateDesc;
	
	//结算单快照
	private String orderContent;
	
	//预估全价
	private BigDecimal totalFee;
	
	//订单预估全价
	private BigDecimal orderTotolFee;
	
	//订单备注金额小计
	private BigDecimal remarksAmt;
	
	//已付金额
	private BigDecimal paidAmt;
	
	//双方需交易金额
	private BigDecimal amt;
	
	//整体备注
	private String remark;
	
	//状态
	private Long stat;
	
	//旅行社名称
	private String taname;

	public String getTaname() {
		return taname;
	}

	public void setTaname(String taname) {
		this.taname = taname;
	}

	public String getStatmentId() {
		return statmentId;
	}

	public void setStatmentId(String statmentId) {
		this.statmentId = statmentId;
	}

	public String getRouteID() {
		return routeID;
	}

	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getStatmentName() {
		return statmentName;
	}

	public void setStatmentName(String statmentName) {
		this.statmentName = statmentName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserSubID() {
		return userSubID;
	}

	public void setUserSubID(String userSubID) {
		this.userSubID = userSubID;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStat() {
		return stat;
	}

	public void setStat(Long stat) {
		this.stat = stat;
	}

	public BigDecimal getOrderTotolFee() {
		return orderTotolFee;
	}

	public void setOrderTotolFee(BigDecimal orderTotolFee) {
		this.orderTotolFee = orderTotolFee;
	}

	public BigDecimal getRemarksAmt() {
		return remarksAmt;
	}

	public void setRemarksAmt(BigDecimal remarksAmt) {
		this.remarksAmt = remarksAmt;
	}

	public String getCreatDateDesc() {
		return creatDateDesc;
	}

	public void setCreatDateDesc(String creatDateDesc) {
		this.creatDateDesc = creatDateDesc;
	}
	
}
