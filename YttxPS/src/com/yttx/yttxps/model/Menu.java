package com.yttx.yttxps.model;

import java.util.List;

public class Menu {
	
	private String id;

	private String name;

	private boolean hasActive;

	private String url;

	private List<Menu> subMenu;

	private boolean hasChild;
	
	private String icon;


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


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}



	public boolean isHasActive() {
		return hasActive;
	}

	public void setHasActive(boolean hasActive) {
		this.hasActive = hasActive;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", hasActive=" + hasActive
				+ ", url=" + url + ", subMenu=" + subMenu + ", hasChild="
				+ hasChild + ", icon=" + icon + "]";
	}
	
	
	

}
