package com.yttx.yttxps.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.yttx.yttxps.model.SysOper;

/**
 * 类描述：用户新增/修改服务器接收页面参数类
 * @author sunchao
 * @date 2016年2月2日 下午4:29:12
 */
public class SysOperSubRequest {
	
	//用户信息
	private SysOper sysOper;
	
	//用户权限列表
	private List<String> rights;
	
	public SysOperSubRequest(){
		sysOper = new SysOper();
		rights = new ArrayList<String>();
	}

	public SysOper getSysOper() {
		return sysOper;
	}

	public void setSysOper(SysOper sysOper) {
		this.sysOper = sysOper;
	}

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
