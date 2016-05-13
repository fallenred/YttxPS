package com.yttx.yttxps.xml.bean.closeList;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 结算单收支明细
 * @author admin
 *
 */
@XStreamAlias("body")
public class Body {
	
	/**
	 * 旅行社名字
	 */
	@XStreamAlias("tname")
	private String tname;
	
	/**
	 * 前台操作员
	 */
	@XStreamAlias("custopername")
	private String custopername;
	
	/**
	 * 计调人员
	 */
	@XStreamAlias("opername")
	private String opername;
	
	/**
	 * 团队总人数
	 */
	@XStreamAlias("visitornum")
	private String visitornum;

	/**
	 * 成本明细
	 */
	@XStreamAlias("costdetails")
	private CostDetails costdetails;
	
	/**
	 * 收入明细
	 */
	@XStreamAlias("incomedetails")
	private IncomeDetails incomedetails;

	/**
	 * 总收入
	 */
	@XStreamAlias("income")
	private BigDecimal income;
	
	/**
	 * 总支出
	 */
	@XStreamAlias("expenditure")
	private BigDecimal expenditure;
	
	/**
	 * 总利润
	 */
	@XStreamAlias("profit")
	private BigDecimal profit;
	
	/**
	 * 保底
	 */
	@XStreamAlias("minproceeds")
	private BigDecimal minproceeds;
	
	/**
	 * 客户总支出
	 */
	@XStreamAlias("custexpend")
	private BigDecimal custexpend;
	
	/**
	 * 客户实际利润
	 */
	@XStreamAlias("custprofit")
	private BigDecimal custprofit;
	
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCustopername() {
		return custopername;
	}

	public void setCustopername(String custopername) {
		this.custopername = custopername;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	public String getVisitornum() {
		return visitornum;
	}

	public void setVisitornum(String visitornum) {
		this.visitornum = visitornum;
	}

	public CostDetails getCostdetails() {
		return costdetails;
	}

	public void setCostdetails(CostDetails costdetails) {
		this.costdetails = costdetails;
	}

	public IncomeDetails getIncomedetails() {
		return incomedetails;
	}

	public void setIncomedetails(IncomeDetails incomedetails) {
		this.incomedetails = incomedetails;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getMinproceeds() {
		return minproceeds;
	}

	public void setMinproceeds(BigDecimal minproceeds) {
		this.minproceeds = minproceeds;
	}

	public BigDecimal getCustexpend() {
		return custexpend;
	}

	public void setCustexpend(BigDecimal custexpend) {
		this.custexpend = custexpend;
	}

	public BigDecimal getCustprofit() {
		return custprofit;
	}

	public void setCustprofit(BigDecimal custprofit) {
		this.custprofit = custprofit;
	}

}
