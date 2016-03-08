package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.CustomInfo;

/**
 * 会员管理模块--会员管理：分页请求会员信息封装类
 */
public class CostomPageRequest extends JqGridRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CustomInfo filters = new CustomInfo();

	public CustomInfo getFilters() {
		return filters;
	}

	public void setFilters(CustomInfo filters) {
		this.filters = filters;
	}

	public void copyCustomFilters(Map<String,Object> map){
		map.put("id",filters.getId());//客户id
		map.put("name",filters.getName());//客户名称
		map.put("taName", filters.getTaname());//旅行社名称
		map.put("stat",filters.getStat());//状态
	}
}
