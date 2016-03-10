package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.CustomInfo;

/**
 * 会员模块：会员审核Service接口
 *
 */
public interface IMemberAuditService {
	/**
	 * 根据过滤条件，请求会员的总条数
	 */
	@Transactional(readOnly=true)
	int selectCountSelective(Map<String, Object> map);

	/**
	 * 分页请求会员审核记录
	 */
	@Transactional(readOnly=true)
	List<CustomInfo> selectSelectivePage(Map<String, Object> map);

	/**
	 * 根据审核编号找到一条审核记录
	 */
	@Transactional(readOnly=true)
	CustomInfo selectCusByAuditNo(String auditNo);

	/**
	 *审核customer 
	 */
	@Transactional
	boolean auditCustomer(CustomInfo auditRec);

}
