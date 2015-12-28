package com.yttx.yttxps.model;

public class SysDep {
	
	private long depNo;
	
	private String depName;
	
	private long stat;

	public long getDepNo() {
		return depNo;
	}

	public void setDepNo(long depNo) {
		this.depNo = depNo;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public long getStat() {
		return stat;
	}

	public void setStat(long stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "SysDep [depNo=" + depNo + ", depName=" + depName + ", stat="
				+ stat + "]";
	}
	
	
	

}
