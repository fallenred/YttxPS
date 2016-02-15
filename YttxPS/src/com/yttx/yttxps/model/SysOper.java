package com.yttx.yttxps.model;

public class SysOper {
	
	private String sysOperId;
	
	private String sysOperName;
	
	private Long	adminType;
	
	private Long	depNo;
	
	private String	depName;
	
	private String sysOperPwd;
	
	private Long pwdStat;
	
	private Long stat;

	
	public String getSysOperId() {
		return sysOperId;
	}


	public void setSysOperId(String sysOperId) {
		this.sysOperId = sysOperId;
	}


	public String getSysOperName() {
		return sysOperName;
	}


	public void setSysOperName(String sysOperName) {
		this.sysOperName = sysOperName;
	}


	public Long getAdminType() {
		return adminType;
	}


	public void setAdminType(Long adminType) {
		this.adminType = adminType;
	}


	public Long getDepNo() {
		return depNo;
	}


	public void setDepNo(Long depNo) {
		this.depNo = depNo;
	}
	

	public String getDepName() {
		return depName;
	}


	public void setDepName(String depName) {
		this.depName = depName;
	}


	public String getSysOperPwd() {
		return sysOperPwd;
	}


	public void setSysOperPwd(String sysOperPwd) {
		this.sysOperPwd = sysOperPwd;
	}


	public Long getPwdStat() {
		return pwdStat;
	}


	public void setPwdStat(Long pwdStat) {
		this.pwdStat = pwdStat;
	}


	public Long getStat() {
		return stat;
	}


	public void setStat(Long stat) {
		this.stat = stat;
	}


	@Override
	public String toString() {
		return "SysOper [sysOperId=" + sysOperId + ", sysOperName="
				+ sysOperName + ", adminType=" + adminType + ", depNo=" + depNo
				+ ", sysOperPwd=" + sysOperPwd + ", pwdStat=" + pwdStat
				+ ", stat=" + stat + "]";
	}
	


}
