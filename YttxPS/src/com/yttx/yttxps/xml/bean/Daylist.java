package com.yttx.yttxps.xml.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



@XStreamAlias("daylist")
public class Daylist {
	/**
	 * 日期标识
	 */
	@XStreamAlias("dayflag")
	private String dayflag;
	
	/**
	 * 资源列表
	 */
	@XStreamImplicit(itemFieldName="reslist")
	private List<Reslist> reslist;
	
	/**
	 * 以下为定制线路时客户询价时录入的字段信息
	 * add by huangtao
	 * add date 2016-03-09
	 */
	//行程日期
	@XStreamAlias("date")
	private String date;
	//交通方式
	@XStreamAlias("transport")
	private String transport;
	//行程内容
	@XStreamAlias("schedule")
	private String schedule;
	//早餐
	@XStreamAlias("breakfast")
	private String breakfast;
	//午餐
	@XStreamAlias("lunch")
	private String lunch;
	//晚餐
	@XStreamAlias("dinner")
	private String dinner;
	//住宿
	@XStreamAlias("hotel")
	private String hotel;
	
	public String getDayflag() {
		return dayflag;
	}
	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
	}
	public List<Reslist> getReslist() {
		return reslist;
	}
	public void setReslist(List<Reslist> reslist) {
		this.reslist = reslist;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	public String getDinner() {
		return dinner;
	}
	public void setDinner(String dinner) {
		this.dinner = dinner;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
}
