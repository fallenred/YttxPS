package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 收入明细
 * @author admin
 *
 */
@XStreamAlias("incomedetails")
public class IncomeDetails {

	/**
	 * 购物店
	 */
	@XStreamImplicit(itemFieldName="shop")
	private Shop shop;
	
	/**
	 * 娱乐
	 */
	@XStreamImplicit(itemFieldName="entertainment")
	private Entertainment entertainment;
	
	/**
	 * 其它收入
	 */
	@XStreamImplicit(itemFieldName="other")
	private Other other;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Entertainment getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(Entertainment entertainment) {
		this.entertainment = entertainment;
	}

	public Other getOther() {
		return other;
	}

	public void setOther(Other other) {
		this.other = other;
	}
	
}
