package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.List;

public class Room {
    private String fsRoomno;
	private String fsAccomno;
	private String fsType;
	private String fsName;
	private String fsMeal;
	private BigDecimal fiStat;
	
	private List<TCCPrice> tccPrices;

	public String getFsRoomno() {
		return fsRoomno;
	}

	public void setFsRoomno(String fsRoomno) {
		this.fsRoomno = fsRoomno == null ? null : fsRoomno.trim();
	}

	public String getFsAccomno() {
		return fsAccomno;
	}

	public void setFsAccomno(String fsAccomno) {
		this.fsAccomno = fsAccomno == null ? null : fsAccomno.trim();
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType == null ? null : fsType.trim();
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName == null ? null : fsName.trim();
	}

	public String getFsMeal() {
		return fsMeal;
	}

	public void setFsMeal(String fsMeal) {
		this.fsMeal = fsMeal == null ? null : fsMeal.trim();
	}

	public BigDecimal getFiStat() {
		return fiStat;
	}

	public void setFiStat(BigDecimal fiStat) {
		this.fiStat = fiStat;
	}

	public List<TCCPrice> getTccPrices() {
		return tccPrices;
	}

	public void setTccPrices(List<TCCPrice> tccPrices) {
		this.tccPrices = tccPrices;
	}
}