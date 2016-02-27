package com.yttx.yttxps.model.vo;

import java.util.Date;
import java.util.Map;

import com.yttx.yttxps.model.TCCPrice;

public class PriceRequest extends TCCPrice {

	private Date fsDate;

	public Date getFsDate() {
		return fsDate;
	}

	public void setFsDate(Date fsDate) {
		this.fsDate = fsDate;
	}
	
	public void copyPage(Map<String, Object> map) {
		map.put("fsResno", super.getFsResno());
		map.put("fsRestype", super.getFsRestype());
		map.put("fsDate", this.getFsDate());
	}
}
