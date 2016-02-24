package com.yttx.yttxps.xml.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



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
	
	public String getDayflag() {
		return dayflag;
	}
	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
	}
	public List<Reslist> getReslists() {
		return reslist;
	}
	public void setReslists(List<Reslist> reslist) {
		this.reslist = reslist;
	}
	
}
