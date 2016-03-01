package com.yttx.yttxps.xml.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

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
	
}
