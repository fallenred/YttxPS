package com.yttx.yttxps.model;

import java.util.List;
import java.util.Map;

public class SessionEntity {
	
	private String id;
	private String name;
	private String pwd;
	private long status;
	private long level;
	private long depNo;
	private String depName;
	private long pwdStatus;
	private Map<String,Menu> customAllowMap;
	private Map<String,Menu> customRejectMap;
	private List<Menu> customTree;
	private String currId;
	private String lastId;
	private String menuext;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}


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


	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	public long getPwdStatus() {
		return pwdStatus;
	}
	public void setPwdStatus(long pwdStatus) {
		this.pwdStatus = pwdStatus;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	public String getLastId() {
		return lastId;
	}
	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	public String getMenuext() {
		return menuext;
	}
	public void setMenuext(String menuext) {
		this.menuext = menuext;
	}

	public List<Menu> getCustomTree() {
		return customTree;
	}
	public void setCustomTree(List<Menu> customTree) {
		this.customTree = customTree;
	}
	public Map<String, Menu> getCustomAllowMap() {
		return customAllowMap;
	}
	public void setCustomAllowMap(Map<String, Menu> customAllowMap) {
		this.customAllowMap = customAllowMap;
	}
	public Map<String, Menu> getCustomRejectMap() {
		return customRejectMap;
	}
	public void setCustomRejectMap(Map<String, Menu> customRejectMap) {
		this.customRejectMap = customRejectMap;
	}
	
	
	

}
