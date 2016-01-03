package com.yttx.yttxps.model.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.yttx.yttxps.model.Scenic;

public class ScenicRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

	private Scenic scenic;

	public void copyScenic(Map<String, Object> map) {
		if (scenic != null) {
			map.put("no", scenic.getNo() == null ? "" : scenic.getNo());
			map.put("name", scenic.getName() == null ? "" : scenic.getName());
			map.put("regionno",
					scenic.getRegionno() == null ? "" : scenic.getRegionno());
			map.put("addr", scenic.getAddr() == null ? "" : scenic.getAddr());
			map.put("lvl", scenic.getLvl() == null ? "" : scenic.getLvl());
			map.put("stat", scenic.getStat() == null ? 1 : scenic.getStat());
			map.put("opentime",
					scenic.getOpentime() == null ? "" : scenic.getOpentime());
			map.put("desc", scenic.getDesc() == null ? "" : scenic.getDesc());
			map.put("speciality",
					scenic.getSpeciality() == null ? "" : scenic
							.getSpeciality());
		}
	}
}