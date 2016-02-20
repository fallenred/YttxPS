package com.yttx.yttxps.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Reslist {
	/**
	 * 资源大类
	 */
	@XStreamAlias("restype")
	private String restype;
	
	/**
	 * 资源属性
	 */
	@XStreamAlias("resprop")
	private String resprop;
	
	/**
	 * 资源编号
	 */
	@XStreamAlias("resno")
	private String resno;
	
	/**
	 * 资源名称
	 */
	@XStreamAlias("resname")
	private String resname;
	
	/**
	 * 消费选项
	 */
	@XStreamAlias("cclist")
	private Cclist cclist;
	
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getResprop() {
		return resprop;
	}
	public void setResprop(String resprop) {
		this.resprop = resprop;
	}
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public Cclist getCclist() {
		return cclist;
	}
	public void setCclist(Cclist cclist) {
		this.cclist = cclist;
	}
}
