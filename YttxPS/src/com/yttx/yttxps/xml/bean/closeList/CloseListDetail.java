package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 结算单收支明细
 * @author admin
 *
 */
@XStreamAlias("body")
public class CloseListDetail {

	/**
	 * 结算单ID
	 */
	@XStreamImplicit(itemFieldName="closeid")
	private String closeid;
	
	/**
	 * 订单ID
	 */
	@XStreamImplicit(itemFieldName="orderid")
	private String orderid;
	
	/**
	 * 总收入
	 */
	@XStreamImplicit(itemFieldName="income")
	private String income;
	
	/**
	 * 总支出
	 */
	@XStreamImplicit(itemFieldName="expenditure")
	private String expenditure;
	
	/**
	 * 总利润
	 */
	@XStreamImplicit(itemFieldName="profit")
	private String profit;
	
	/**
	 * 成本明细
	 */
	@XStreamImplicit(itemFieldName="costdetails")
	private CostDetails costDetails;
	
	/**
	 * 收入明细
	 */
	@XStreamImplicit(itemFieldName="incomedetails")
	private IncomeDetails incomeDetails;

	public String getCloseid() {
		return closeid;
	}

	public void setCloseid(String closeid) {
		this.closeid = closeid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

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
