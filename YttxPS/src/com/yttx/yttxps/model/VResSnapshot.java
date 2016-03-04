package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class VResSnapshot {

	private String fsRouteno;
	
	private BigDecimal fiDayflag;
	
	private String resType;
	
	private String resProp;
	
	private String resNo;
	
	private String resName;

	public String getFsRouteno() {
		return fsRouteno;
	}

	public void setFsRouteno(String fsRouteno) {
		this.fsRouteno = fsRouteno;
	}

	public BigDecimal getFiDayflag() {
		return fiDayflag;
	}

	public void setFiDayflag(BigDecimal fiDayflag) {
		this.fiDayflag = fiDayflag;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResProp() {
		return resProp;
	}

	public void setResProp(String resProp) {
		this.resProp = resProp;
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}
}
