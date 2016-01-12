package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Ttransport;

public class TransportRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ttransport getTransport() {
		return transport;
	}

	public void setTransport(Ttransport transport) {
		this.transport = transport;
	}

	private Ttransport transport;

	public void copyTransport(Map<String, Object> map) {
		if (transport != null) {
			map.put("fsName", transport.getFsName() == null ? "" : transport.getFsName());
			map.put("fiLoadMin", transport.getFiLoadMin() == null ? "" : transport.getFiLoadMin());
			map.put("fiLoadMax", transport.getFiLoadMax() == null ? "" : transport.getFiLoadMax());
			map.put("fiStat", transport.getFiStat() == null ? "" : transport.getFiStat());
		}
	}
}