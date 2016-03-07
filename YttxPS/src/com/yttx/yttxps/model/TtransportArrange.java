package com.yttx.yttxps.model;

import java.math.BigDecimal;


public class TtransportArrange extends TtransportArrangeKey {
	private String fiGenName;
	private String fsTransName;
	private String ftStartdate;
	private BigDecimal fdPrice;
	private String ftEnddate;
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
	public String getFtStartdate() {
		return ftStartdate;
	}
	public void setFtStartdate(String ftStartdate) {
		this.ftStartdate = ftStartdate;
	}
	public BigDecimal getFdPrice() {
		return fdPrice;
	}
	public void setFdPrice(BigDecimal fdPrice) {
		this.fdPrice = fdPrice;
	}
	public String getFtEnddate() {
		return ftEnddate;
	}
	public void setFtEnddate(String ftEnddate) {
		this.ftEnddate = ftEnddate;
	}
}