package com.yttx.yttxps.xml.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("reslist")
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
	 * 客户备注
	 */
	@XStreamAlias("remark")
	private String remark;
	
	/**
	 * 午餐、晚餐标志
	 */
	@XStreamAlias("flag")
	private String flag;
	
	/**
	 * 消费选项
	 */
	@XStreamImplicit(itemFieldName="cclist")
	private List<Cclist> cclist;
	
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
	public List<Cclist> getCclist() {
		return cclist;
	}
	public void setCclist(List<Cclist> cclist) {
		this.cclist = cclist;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
