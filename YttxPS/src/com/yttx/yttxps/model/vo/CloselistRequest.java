package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.TOrderlist;

public class CloselistRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TCloselist getCloselist() {
		return closelist;
	}

	public void setCloselist(TCloselist closelist) {
		this.closelist = closelist;
	}

	private TCloselist closelist;

	public void copyOrderlist(Map<String, Object> map) {
		if (closelist != null) {
			map.put("fsNo", closelist.getFsNo() == null ? "" : closelist.getFsNo());
			map.put("fsName", closelist.getFsName() == null ? "" : closelist.getFsName());
			map.put("fsRoutename", closelist.getFsRoutename() == null ? "" : closelist.getFsRoutename());
			map.put("fiStat", closelist.getFiStat() == null ? "" : closelist.getFiStat());
		}
	}
}