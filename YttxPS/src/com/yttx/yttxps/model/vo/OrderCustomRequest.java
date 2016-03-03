package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TOrderCustom;

public class OrderCustomRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TOrderCustom getOrderCustom() {
		return orderCustom;
	}

	public void setOrderCustom(TOrderCustom orderCustom) {
		this.orderCustom = orderCustom;
	}

	private TOrderCustom orderCustom;

	public void copyOrderCustom(Map<String, Object> map) {
		if (orderCustom != null) {
			map.put("fsOrderID", orderCustom.getFsOrderId() == null ? "" : orderCustom.getFsOrderId());
			map.put("fiSeq", orderCustom.getFiSeq() == null ? "" : orderCustom.getFiSeq());
			map.put("ftCreatDate", orderCustom.getFtCreatdate() == null ? "" : orderCustom.getFtCreatdate());
		}
	}
}