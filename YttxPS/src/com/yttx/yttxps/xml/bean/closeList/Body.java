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
	private CostDetails costDetails;
	
	/**
	 * 收入明细
	 */
	@XStreamAlias("incomedetails")
	private IncomeDetails incomeDetails;

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

	public CostDetails getCostDetails() {
		return costDetails;
	}

	public void setCostDetails(CostDetails costDetails) {
		this.costDetails = costDetails;
	}

	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(IncomeDetails incomeDetails) {
		this.incomeDetails = incomeDetails;
	}
	
}
