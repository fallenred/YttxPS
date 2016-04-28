package com.yttx.yttxps.xml.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("body")
public class Body {
	
	/**
	 * 日期列表
	 */
	@XStreamImplicit(itemFieldName="daylist")
	private List<Daylist> daylist;

	@XStreamImplicit(itemFieldName="reslist")
	private List<Reslist> reslist;
	
	@XStreamAlias("dayflag")
	private String dayflag;
	
	/**
	 * 以下为定制线路客户询价时录入的字段内容
	 * add by huangtao
	 * add date 2016-03-09
	 */
	@XStreamImplicit(itemFieldName="attachs")
	//附件
	private List<Attachs> attachs;
	//服务标准
	@XStreamAlias("svcstdcontent")
	private String svcstdcontent;
	//价格
	@XStreamAlias("price")
	private String price;
	//保险价格
	@XStreamAlias("insuerprice")
	private String insuerprice;
	//订单批次主键
	@XStreamOmitField
	private String fiId;
	//成人
	@XStreamAlias("adult")
	private String adult;
	//儿童
	@XStreamAlias("children")
	private String children;
	//全陪
	@XStreamAlias("fullguide")
	private String fullguide;

	public List<Daylist> getDaylist() {
		return daylist;
	}

	public void setDaylist(List<Daylist> daylist) {
		this.daylist = daylist;
	}

	public List<Reslist> getReslist() {
		return reslist;
	}

	public void setReslist(List<Reslist> reslist) {
		this.reslist = reslist;
	}

	public String getDayflag() {
		return dayflag;
	}

	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
	}

	public String getSvcstdcontent() {
		return svcstdcontent;
	}

	public void setSvcstdcontent(String svcstdcontent) {
		this.svcstdcontent = svcstdcontent;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Attachs> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Attachs> attachs) {
		this.attachs = attachs;
	}

	public String getInsuerprice() {
		return insuerprice;
	}

	public void setInsuerprice(String insuerprice) {
		this.insuerprice = insuerprice;
	}

	public String getFiId() {
		return fiId;
	}

	public void setFiId(String fiId) {
		this.fiId = fiId;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getFullguide() {
		return fullguide;
	}

	public void setFullguide(String fullguide) {
		this.fullguide = fullguide;
	}
}
