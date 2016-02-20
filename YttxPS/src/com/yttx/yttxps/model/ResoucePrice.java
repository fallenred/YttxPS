package com.yttx.yttxps.model;

import java.math.BigDecimal;
public class ResoucePrice {
	//某一天
	private String date;
	
	//资源类型
	private String resType;
	
	//资源编号
	private String resNo;
	
	//消费选项编号
	private String costNo;
	
	//价格
	private BigDecimal price;
	
	//开始时间
	private String startDate;
	
	//结束时间
	private String endDate;

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = (date==null?null:date.trim());
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = (resType==null?null:resType.trim());
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = (resNo==null?null:resNo.trim());
	}

	public String getCostNo() {
		return costNo;
	}

	public void setCostNo(String costNo) {
		this.costNo =(costNo == null?null:costNo.trim());
	}
	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = (startDate==null?null:startDate.trim());
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = (endDate ==null?null:endDate.trim());
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}