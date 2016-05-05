package com.yttx.yttxps.xml.bean.closeList;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 娱乐
 * @author admin
 *
 */
@XStreamAlias("entertainment")
public class Entertainment {

	/**
	 * 合计
	 */
	@XStreamImplicit(itemFieldName="total")
	private String total;

	/**
	 * 数据集合
	 */
	@XStreamImplicit(itemFieldName="reslist")
	private List<ResultList> resultList;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<ResultList> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResultList> resultList) {
		this.resultList = resultList;
	}

}
