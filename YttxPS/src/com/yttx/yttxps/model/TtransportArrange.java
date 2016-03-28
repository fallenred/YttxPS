package com.yttx.yttxps.model;

import java.util.List;

public class TtransportArrange extends TtransportArrangeKey {
	private String fiGenName;
	private String fsTransName;
	
	private List<TCCPrice> tccPrices;

	public String getFiGenName() {
		return fiGenName;
	}

	public void setFiGenName(String fiGenName) {
		this.fiGenName = fiGenName;
	}

	public String getFsTransName() {
		return fsTransName;
	}

	public void setFsTransName(String fsTransName) {
		this.fsTransName = fsTransName;
	}

	public List<TCCPrice> getTccPrices() {
		return tccPrices;
	}

	public void setTccPrices(List<TCCPrice> tccPrices) {
		this.tccPrices = tccPrices;
	}
}