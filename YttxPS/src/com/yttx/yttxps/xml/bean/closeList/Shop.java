package com.yttx.yttxps.xml.bean.closeList;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 购物店
 * @author admin
 *
 */
@XStreamAlias("shop")
public class Shop {

	/**
	 * 合计
	 */
	@XStreamAlias("total")
	private String total;

	/**
	 * 数据集合
	 */
	@XStreamImplicit(itemFieldName="reslist")
	private List<Reslist> reslist;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<Reslist> getReslist() {
		return reslist;
	}

	public void setReslist(List<Reslist> reslist) {
		this.reslist = reslist;
	}

}
