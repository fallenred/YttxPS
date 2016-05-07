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
	 * 数据集合
	 */
	@XStreamImplicit(itemFieldName="reslist")
	private List<Reslist> reslist;

	/**
	 * 合计
	 */
	@XStreamAlias("total")
	private String total;
	
	public List<Reslist> getReslist() {
		return reslist;
	}

	public void setReslist(List<Reslist> reslist) {
		this.reslist = reslist;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
