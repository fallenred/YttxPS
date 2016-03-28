package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransportarrangePrice {
	
	private String fsResno;
	
	private BigDecimal fiGenindex;
	
	private String tgenname;
	
	private String fsTransNo;
	
	private String transName;
	
	private String fsRestype;
	
	private String fsCcno;
	
	private String fsCcname;
	
	private Date ftStartdate;
	
	private Date ftEnddate;

	private BigDecimal fdPrice;

	public String getFsResno() {
		return fsResno;
	}

	public void setFsResno(String fsResno) {
		this.fsResno = fsResno;
	}

	public BigDecimal getFiGenindex() {
		return fiGenindex;
	}

	public void setFiGenindex(BigDecimal fiGenindex) {
		this.fiGenindex = fiGenindex;
	}

	public String getTgenname() {
		return tgenname;
	}

	public void setTgenname(String tgenname) {
		this.tgenname = tgenname;
	}

	public String getFsTransNo() {
		return fsTransNo;
	}

	public void setFsTransNo(String fsTransNo) {
		this.fsTransNo = fsTransNo;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getFsRestype() {
		return fsRestype;
	}

	public void setFsRestype(String fsRestype) {
		this.fsRestype = fsRestype;
	}

	public String getFsCcno() {
		return fsCcno;
	}

	public void setFsCcno(String fsCcno) {
		this.fsCcno = fsCcno;
	}

	public String getFsCcname() {
		return fsCcname;
	}

	public void setFsCcname(String fsCcname) {
		this.fsCcname = fsCcname;
	}

	public Date getFtStartdate() {
		return ftStartdate;
	}

	public void setFtStartdate(Date ftStartdate) {
		this.ftStartdate = ftStartdate;
	}

	public Date getFtEnddate() {
		return ftEnddate;
	}

	public void setFtEnddate(Date ftEnddate) {
		this.ftEnddate = ftEnddate;
	}

	public BigDecimal getFdPrice() {
		return fdPrice;
	}

	public void setFdPrice(BigDecimal fdPrice) {
		this.fdPrice = fdPrice;
	}
}
