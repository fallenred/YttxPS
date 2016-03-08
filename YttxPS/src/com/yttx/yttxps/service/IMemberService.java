package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.CustomInfo;

/**
 * 会员管理模块：会员管理Service
 */
public interface IMemberService{
	/**
	 * 根据过滤条件，请求会员的总条数
	 */
	int selectCountSelective(Map<String, Object> map) ;
	
	/**
	 * 根据过滤条件，分页请求会员信息列表
	 */
	List<CustomInfo> selectSelectivePage(Map<String, Object> map);

	/**
	 * 通过id找到一个会员的详细信息
	 */
	CustomInfo selectCusById(String id);
	
	/**
	 * 动态修改客户信息
	 */
	boolean updateCusSelective(CustomInfo customInfo);

}
