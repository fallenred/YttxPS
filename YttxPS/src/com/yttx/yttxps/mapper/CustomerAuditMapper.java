package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.CustomInfo;

public interface CustomerAuditMapper extends IBaseMapper<CustomInfo>{
	/**
	 * 根据审核编号找到此条审核记录
	 */
	CustomInfo selectByAuditNo(String auditNo);

	/**
	 * 动态更新该条记录
	 */
	boolean updateByPrimaryKeySelective(CustomInfo auditRec);

}
