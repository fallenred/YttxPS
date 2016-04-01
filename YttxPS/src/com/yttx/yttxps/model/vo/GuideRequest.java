package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TguideExample;
import com.yttx.yttxps.model.TguideExample.Criteria;

public class GuideRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tguide getGuide() {
		return guide;
	}

	public void setGuide(Tguide guide) {
		this.guide = guide;
	}

	private Tguide guide;

	public void copyGuide(Map<String, Object> map) {
		if (guide != null) {
			map.put("no", guide.getNo());
			map.put("name", guide.getName());
			map.put("gender", guide.getGender());
			map.put("idno", guide.getIdno());
			map.put("workdate", guide.getWorkdate());
			map.put("contactno", guide.getContactno());
			map.put("preferteem", guide.getPreferteem());
			map.put("speciality", guide.getSpeciality());
			map.put("desc", guide.getDesc());
			map.put("lvl", guide.getLvl());
			map.put("salary", guide.getSalary());
			map.put("daysale", guide.getDaysale());
			map.put("weeksale", guide.getWeeksale());
			map.put("monthsale", guide.getMonthsale());
			map.put("stat", guide.getStat());
		}
	}
	
	public Criteria copyTguide(TguideExample example) {
		Criteria criteria = example.createCriteria();
		if (guide != null) {
			if (guide.getLvl() != null)
				criteria.andFsLvlEqualTo(guide.getLvl());
			if (guide.getStat() != null)
				criteria.andFiStatEqualTo(guide.getStat());
		}
		return criteria;
	}
}