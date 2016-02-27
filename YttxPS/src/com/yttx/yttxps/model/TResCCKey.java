package com.yttx.yttxps.model;

import java.util.HashMap;
import java.util.Map;

public class TResCCKey {

	private String fsRestype;
	private String fsCcno;
	private String fsResname;
	private String fsCcname;

	public String getFsRestype() {
		return fsRestype;
	}

	public void setFsRestype(String fsRestype) {
		this.fsRestype = fsRestype == null ? null : fsRestype.trim();
	}

	public String getFsCcno() {
		return fsCcno;
	}

	public void setFsCcno(String fsCcno) {
		this.fsCcno = fsCcno == null ? null : fsCcno.trim();
	}

	public String getFsResname() {
		return fsResname;
	}

	public void setFsResname(String fsResname) {
		this.fsResname = fsResname == null ? null : fsResname.trim();
	}

	public String getFsCcname() {
		return fsCcname;
	}

	public void setFsCcname(String fsCcname) {
		this.fsCcname = fsCcname == null ? null : fsCcname.trim();
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fsRestype", fsRestype);
		map.put("fsCcno", fsCcno);
		return map;
	}
}