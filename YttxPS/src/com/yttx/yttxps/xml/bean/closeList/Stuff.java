package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 商品明细
 * @author admin
 *
 */
@XStreamAlias("cclist")
public class Stuff {

	/**
	 * 购物消费项目类型编号 字典编号 牦牛肉 水晶店 银器
	 */
	@XStreamAlias("typeno")
	private String typeno;
	
	/**
	 * 购物消费项目类型名 牦牛肉 水晶店 银器
	 */
	@XStreamAlias("typename")
	private String typename;
	
	/**
	 * 打单金额
	 */
	@XStreamAlias("consumption")
	private String consumption;
	
	/**
	 * 返佣比例
	 */
	@XStreamAlias("proportion")
	private String proportion;
	
	/**
	 * 返佣金额
	 */
	@XStreamAlias("profit")
	private String profit;
	
	/**
	 * 备注
	 */
	@XStreamAlias("remark")
	private String remark;

	public String getTypeno() {
		return typeno;
	}

	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
