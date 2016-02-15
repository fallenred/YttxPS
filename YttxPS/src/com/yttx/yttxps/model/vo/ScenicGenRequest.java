package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TScenicGen;
import com.yttx.yttxps.model.TScenicGenExample;
import com.yttx.yttxps.model.TScenicGenExample.Criteria;

public class ScenicGenRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TScenicGen getScenicGen() {
		return scenicGen;
	}

	public void setScenicGen(TScenicGen scenicGen) {
		this.scenicGen = scenicGen;
	}

	private TScenicGen scenicGen;

	public void copyScenicGen(Map<String, Object> map) {
		if (scenicGen != null) {
		}
	}
	
	public void copyScenicGen(TScenicGenExample example) {
		if (scenicGen != null) {
			Criteria criteria = example.createCriteria();
			if (scenicGen.getFiGenindex() != null) 
				criteria.andFiGenindexEqualTo(scenicGen.getFiGenindex());
		}
	}
}