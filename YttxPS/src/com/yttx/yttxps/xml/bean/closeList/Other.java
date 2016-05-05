package com.yttx.yttxps.xml.bean.closeList;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 其他收支
 * @author admin
 *
 */
@XStreamAlias("other")
public class Other {

	/**
	 * 合计
	 */
	@XStreamAlias("total")
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
