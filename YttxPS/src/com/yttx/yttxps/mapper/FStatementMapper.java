package com.yttx.yttxps.mapper;

import java.util.Date;

import com.yttx.yttxps.model.corder.FStatement;

/**
 * @author sunchao
 * @date 2016年2月25日 下午4:28:24
 */
public interface FStatementMapper<T> extends IBaseMapper<T>{

	/**
	 * 通过订单id找到结算单
	 */
	FStatement selectFSByOrderId(String orderId);

	/**
	 * 从序列中获取一个id
	 */
	int generateFSId();

	/**
	 * 插入一条记录
	 */
	void insert(FStatement fStatement);

	/**
	 * 通过结算单号fsId找到一条记录
	 */
	FStatement selectFSById(String fsId);

	/**
	 * 更新数据库中的一条结算单记录
	 */
	boolean updateFSSelective(FStatement fStatement);
	
	Date getCurrentSysdate();
}
