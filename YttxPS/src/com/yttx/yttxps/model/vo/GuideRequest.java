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
			map.put("no", guide.getNo() == null ? "" : guide.getNo());
			map.put("name", guide.getName() == null ? "" : guide.getName());
			map.put("o",guide.getGender() == null ? "" : guide.getGender());
			map.put("idno",guide.getIdno() == null ? "" : guide.getIdno());
			map.put("workdate", guide.getWorkdate()== null ? "" : guide.getWorkdate());
			map.put("contactno", guide.getContactno() == null ? "" : guide.getContactno());
			map.put("preferteem", guide.getPreferteem()== null ? 1 : guide.getPreferteem());
			map.put("speciality",guide.getSpeciality() == null ? "" : guide.getSpeciality());
			map.put("desc", guide.getDesc() == null ? "" : guide.getDesc());
			map.put("lvl",guide.getLvl() == null ? "" : guide.getLvl());
			map.put("salary",guide.getSalary() == null ? "" : guide.getSalary());
			map.put("daysale",guide.getDaysale() == null ? "" : guide.getDaysale());
			map.put("weeksale",guide.getWeeksale() == null ? "" : guide.getWeeksale());
			map.put("monthsale",guide.getMonthsale() == null ? "" : guide.getMonthsale());
			map.put("stat",guide.getStat() == null ? "" : guide.getStat());
		}
	}
	
	public void copyTguide(TguideExample example) {
		if (guide != null) {
			Criteria criteria = example.createCriteria();
			if (guide.getLvl() != null)
				criteria.andFsLvlEqualTo(guide.getLvl());
		}
	}
}