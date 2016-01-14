package com.yttx.yttxps.model.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.yttx.yttxps.model.CustChgAu;
import com.yttx.yttxps.model.Scenic;

public class CustChgAuRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustChgAu getCustChgAu() {
		return custChgAu;
	}

	public void setScenic(CustChgAu custChgAu) {
		this.custChgAu = custChgAu;
	}

	private CustChgAu custChgAu;

	public void copyCustChgAu(Map<String, Object> map) {
		if (custChgAu != null) {
			map.put("no", custChgAu.getId() == null ? "" : custChgAu.getId());
		}
	}
}