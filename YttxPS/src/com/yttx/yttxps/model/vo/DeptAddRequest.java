package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yttx.yttxps.model.SysDep;

/**
 * 类描述：新增部门请求类
 * @author sunchao
 * @date 2016年1月28日 下午2:57:44
 */
public class DeptAddRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SysDep sysDep;
	
	private List<String> rights;
	
	public DeptAddRequest(){
		super();
		sysDep=new SysDep();
		sysDep.setStat(1L);
		rights=new ArrayList<String>();
	}

	public SysDep getSysDep() {
		return sysDep;
	}

	public void setSysDep(SysDep sysDep) {
		this.sysDep = sysDep;
	}

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}
}
