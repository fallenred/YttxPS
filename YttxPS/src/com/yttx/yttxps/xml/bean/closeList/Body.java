package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 结算单收支明细
 * @author admin
 *
 */
@XStreamAlias("body")
public class Body {

	/**
	 * 总收入
	 */
	@XStreamAlias("income")
	private String income;
	
	/**
	 * 总支出
	 */
	@XStreamAlias("expenditure")
	private String expenditure;
	
	/**
	 * 总利润
	 */
	@XStreamAlias("profit")
	private String profit;
	
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

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
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

}
