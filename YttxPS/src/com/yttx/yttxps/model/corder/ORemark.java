package com.yttx.yttxps.model.corder;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类描述：订单备注
 * @author sunchao
 * @date 2016年2月23日 下午3:57:19
 */
public class ORemark {
	//归属订单ID
	private String orderId;
	
	//备注顺序号
	private Integer seq;
	
	//后台计调ID
	private String operId;
	
	//日期时间
	private Date date;
	private String dateDesc;
	
	//备注内容
	private String content;
	
	//发生交易金额
	private BigDecimal amt;
	
	private BigDecimal paidAmt;
	
	//备注状态
	private Integer stat;
	
	
	private Integer closeStat;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = (orderId == null?null:orderId.trim());
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}
	
	public String getDateDesc() {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		return df.format(date);
	}

	public void setDateDesc(String dateDesc) {
		this.dateDesc = dateDesc;
	}

	
	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public Integer getCloseStat() {
		return closeStat;
	}

	public void setCloseStat(Integer closeStat) {
		this.closeStat = closeStat;
	}

	@Override
	public String toString() {
		return "ORemark [orderId=" + orderId + ", seq=" + seq + ", operId=" + operId + ", date=" + date + ", dateDesc="
				+ dateDesc + ", content=" + content + ", amt=" + amt + ", stat=" + stat + "]";
	}
}
