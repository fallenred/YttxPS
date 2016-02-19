package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.SysDep;

/**
 * 类描述：用户机构客户端分页请求
 * @author sunchao
 * @date 2016年1月28日 下午2:57:44
 */
public class SysDeptRequest extends JqGridRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SysDep sysDep = new SysDep();
	

	public SysDep getSysDep() {
		return sysDep;
	}

	public void setSysDep(SysDep sysDep) {
		this.sysDep = sysDep;
	}

	public void copySysDep(Map<String, Object> map) {
		if (sysDep != null) {
			map.put("depno", sysDep.getDepNo() == null ? "" : sysDep.getDepNo());
			map.put("depname", sysDep.getDepName() == null ? "" : sysDep.getDepName());
			map.put("stat",sysDep.getStat() == null ?"" : sysDep.getStat());
		}
	}
}
