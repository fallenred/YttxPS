package com.yttx.yttxps.model.vo;

import java.util.Arrays;
import java.util.Map;

import com.yttx.yttxps.model.Accomadation;

/**
 * 车辆信息和视图映射
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 * 
 */
public class AccomadationRequest extends JqGridRequest implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Accomadation getAccomadation() {
		return accomadation;
	}

	public void setAccomadation(Accomadation accomadation) {
		this.accomadation = accomadation;
	}

	public String[] getScenicNo() {
		return scenicNo;
	}

	public void setScenicNo(String[] scenicNo) {
		this.scenicNo = scenicNo;
	}

	private Accomadation accomadation;
	
	private String scenicNo[];

	public void copyAccomadation(Map<String, Object> map) {
		if (accomadation != null) {
			map.put("no",  accomadation.getNo());
			map.put("regionno",  accomadation.getRegionno());
			map.put("starlvl", accomadation.getStarlvl());
			map.put("name", accomadation.getName());
			map.put("addr",  accomadation.getAddr());
			map.put("speciality", accomadation.getSpeciality());
			map.put("stat", accomadation.getStat());
			map.put("desc", accomadation.getDesc());
			
		}
		
		if(scenicNo != null) {
			map.put("scenicNo", Arrays.asList(scenicNo));
		}
	}
}