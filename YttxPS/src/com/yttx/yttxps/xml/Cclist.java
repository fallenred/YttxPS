package com.yttx.yttxps.xml;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Cclist {
	/**
	 * 选项编号
	 */
	@XStreamAlias("ccno")
	private String ccno;
	/**
	 * 选项名称
	 */
	@XStreamAlias("ccname")
	private String ccname;
	/**
	 * 选项价格
	 */
	@XStreamAlias("price")
	private BigDecimal price;
	/**
	 * 选项类型(0和1,0代表不计价,1代表计价)
	 */
	@XStreamAlias("cctype")
	private String cctype;
	/**
	 * 实际消费人数(线路永远为1)
	 */
	private String usernum;
	
	public String getCcno() {
		return ccno;
	}
	public void setCcno(String ccno) {
		this.ccno = ccno;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCctype() {
		return cctype;
	}
	public void setCctype(String cctype) {
		this.cctype = cctype;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	
}
