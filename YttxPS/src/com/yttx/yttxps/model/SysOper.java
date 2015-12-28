package com.yttx.yttxps.model;

public class SysOper {
	
	private String sysOperId;
	
	private String sysOperName;
	
	private long	adminType;
	
	private long	depNo;
	
	private String sysOperPwd;
	
	private long pwdStat;
	
	private long stat;



	public String getSysOperName() {
		return sysOperName;
	}

	public void setSysOperName(String sysOperName) {
		this.sysOperName = sysOperName;
	}

	public long getAdminType() {
		return adminType;
	}

	public void setAdminType(long adminType) {
		this.adminType = adminType;
	}



	public String getSysOperId() {
		return sysOperId;
	}

	public void setSysOperId(String sysOperId) {
		this.sysOperId = sysOperId;
	}

	public long getDepNo() {
		return depNo;
	}

	public void setDepNo(long depNo) {
		this.depNo = depNo;
	}

	public String getSysOperPwd() {
		return sysOperPwd;
	}

	public void setSysOperPwd(String sysOperPwd) {
		this.sysOperPwd = sysOperPwd;
	}

	public long getPwdStat() {
		return pwdStat;
	}

	public void setPwdStat(long pwdStat) {
		this.pwdStat = pwdStat;
	}

	public long getStat() {
		return stat;
	}

	public void setStat(long stat) {
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
