package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Tgen;

public class GenRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tgen getGen() {
		return gen;
	}

	public void setGen(Tgen gen) {
		this.gen = gen;
	}

	private Tgen gen;

	public void copyGen(Map<String, Object> map) {
		if (gen != null) {
			map.put("fsName", gen.getFsName() == null ? "" : gen.getFsName());
			map.put("fiDays", gen.getFiDays() == null ? "" : gen.getFiDays());
			map.put("fiStat", gen.getFiStat() == null ? "" : gen.getFiStat());
		}
	}
}