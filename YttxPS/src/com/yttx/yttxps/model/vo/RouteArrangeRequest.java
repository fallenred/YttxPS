package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeExample.Criteria;

public class RouteArrangeRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TRouteArrange getArrange() {
		return arrange;
	}

	public void setArrange(TRouteArrange arrange) {
		this.arrange = arrange;
	}

	public boolean isHasRouteCC() {
		return hasRouteCC;
	}

	public void setHasRouteCC(boolean hasRouteCC) {
		this.hasRouteCC = hasRouteCC;
	}

	private TRouteArrange arrange;
	
	private boolean hasRouteCC = false;

	public void copyTRouteArrange(Map<String, Object> map) {
		if (arrange != null) {
			map.put("fsName", arrange.getFsName() == null ? "" : arrange.getFsName());
			map.put("fiGenindex", arrange.getFiGenindex() == null ? "" : arrange.getFiGenindex());
			map.put("fiStat", arrange.getFiStat() == null ? "" : arrange.getFiStat());
		}
		map.put("hasRouteCC", hasRouteCC);
	}
	
	public void copyTRouteArrange(TRouteArrangeExample example) {
		if (arrange != null) {
			Criteria criteria = example.createCriteria();
			if (arrange.getFsName() != null) 
				criteria.andFsNameLike(arrange.getFsName());
			if (arrange.getFiGenindex() != null)
				criteria.andFiGenindexEqualTo(arrange.getFiGenindex());
			if (arrange.getFiStat() != null)
				criteria.andFiStatEqualTo(arrange.getFiStat());
		}
	}
}