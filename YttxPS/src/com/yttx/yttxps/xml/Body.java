package com.yttx.yttxps.xml;

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

	private List<Reslist> reslist;

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
	
}
