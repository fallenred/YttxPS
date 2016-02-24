package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.TEntertainment;

public class EntertainmentRequest extends JqGridRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3984820560063031329L;
	
	private TEntertainment entertainment;

	public TEntertainment getEntertainment() {
		return entertainment;
	}

	public void setEntertainment(TEntertainment entertainment) {
		this.entertainment = entertainment;
	}

	public void copyEntertainment(Map<String, Object> map) {
		if (entertainment != null) {
			map.put("fsNo", entertainment.getFsNo() == null ? "" : entertainment.getFsNo());   //娱乐项目代码
			map.put("fsName", entertainment.getFsName() == null ? "" : entertainment.getFsName());   //娱乐项目名称
			map.put("fsScenicno", entertainment.getFsRegionno() == null ? "" : entertainment.getFsRegionno());   //所属地区
			map.put("fsType", entertainment.getFsType() == null ? "" : entertainment.getFsType());   //娱乐项目类型
			map.put("fiStat", entertainment.getFiStat() == null ? "" : entertainment.getFiStat());   //状态
		}
	}
}
