package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;


public class TtransportArrange extends TtransportArrangeKey {
	private String fiGenName;
	private String fsTransName;
	private Date ftStartdate;
	private BigDecimal fdPrice;
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
	public Date getFtStartdate() {
		return ftStartdate;
	}
	public void setFtStartdate(Date ftStartdate) {
		this.ftStartdate = ftStartdate;
	}
	public BigDecimal getFdPrice() {
		return fdPrice;
	}
	public void setFdPrice(BigDecimal fdPrice) {
		this.fdPrice = fdPrice;
	}
}