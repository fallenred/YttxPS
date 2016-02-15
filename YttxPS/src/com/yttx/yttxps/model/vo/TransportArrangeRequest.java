package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.TtransportArrangeExample.Criteria;

public class TransportArrangeRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TtransportArrange getTransportArrange() {
		return transportArrange;
	}

	public void setTransportArrange(TtransportArrange transportArrange) {
		this.transportArrange = transportArrange;
	}

	private TtransportArrange transportArrange;

	public void copyTransportArrange(Map<String, Object> map) {
		if (transportArrange != null) {
			map.put("fiGenName", transportArrange.getFiGenName() == null ? "" : transportArrange.getFiGenName());
			map.put("fsTransName", transportArrange.getFsTransName() == null ? "" : transportArrange.getFsTransName());
		}
	}
	
	public void copyTransportArrange(TtransportArrangeExample example) {
		if (transportArrange != null) {
			Criteria criteria = example.createCriteria();
			if (transportArrange.getFiGenindex() != null)
				criteria.andFiGenindexEqualTo(transportArrange.getFiGenindex());
		}
	}
}