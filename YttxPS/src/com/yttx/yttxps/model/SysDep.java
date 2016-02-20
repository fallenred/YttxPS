package com.yttx.yttxps.model;

public class SysDep {
	
	private Long depNo;
	
	private String depName;
	
	private Long stat;

	

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



	public Long getStat() {
		return stat;
	}



	public void setStat(Long stat) {
		this.stat = stat;
	}



	@Override
	public String toString() {
		return "SysDep [depNo=" + depNo + ", depName=" + depName + ", stat=" + stat + "]";
	}
}
