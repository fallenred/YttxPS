package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

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
	@XStreamImplicit(itemFieldName="typeno")
	private String typeNo;
	
	/**
	 * 购物消费项目类型名 牦牛肉 水晶店 银器
	 */
	@XStreamImplicit(itemFieldName="typename")
	private String typeName;
	
	/**
	 * 打单金额
	 */
	@XStreamImplicit(itemFieldName="consumption")
	private String consumption;
	
	/**
	 * 返佣比例
	 */
	@XStreamImplicit(itemFieldName="proportion")
	private String proportion;
	
	/**
	 * 返佣金额
	 */
	@XStreamImplicit(itemFieldName="profit")
	private String profit;
	
	/**
	 * 备注
	 */
	@XStreamImplicit(itemFieldName="remark")
	private String remark;

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
