package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TOrderlist;

public class OrderlistRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TOrderlist getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(TOrderlist orderlist) {
		this.orderlist = orderlist;
	}

	private TOrderlist orderlist;

	public void copyOrderlist(Map<String, Object> map) {
		if (orderlist != null) {
			map.put("fsNo", orderlist.getFsNo() == null ? "" : orderlist.getFsNo());
			map.put("fsName", orderlist.getFsName() == null ? "" : orderlist.getFsName());
			map.put("fsType", orderlist.getFsType() == null ? "" : orderlist.getFsType());
			map.put("fiStat", orderlist.getFiStat() == null ? "" : orderlist.getFiStat());
		}
	}
}