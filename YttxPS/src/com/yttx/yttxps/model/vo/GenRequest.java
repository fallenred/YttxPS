package com.yttx.yttxps.model.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.yttx.yttxps.model.Tgen;
import com.yttx.yttxps.model.TgenExample;
import com.yttx.yttxps.model.TgenExample.Criteria;

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
	
	public void copyGen(TgenExample example) {
		if (gen != null) {
			Criteria criteria = example.createCriteria();
			if (gen.getFsName() != null) 
				criteria.andFsNameLike(gen.getFsName());
			if (gen.getFiDays() != null)
			criteria.andFiDaysEqualTo(gen.getFiDays());
			if (gen.getFiStat() != null)
				criteria.andFiStatEqualTo(gen.getFiStat());
		}
	}
}