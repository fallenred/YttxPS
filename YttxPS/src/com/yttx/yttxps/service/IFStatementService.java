package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.corder.FStatement;

/**
 * 类描述：结算单service
 * @author sunchao
 * @date 2016年2月26日 上午11:10:50
 */
public interface IFStatementService {

	/**
	 * 分页查找结算单的list
	 */
	List<FStatement> selectSelectivePage(Map<String, Object> map);

	/**
	 * 通过结算单号找到结算单内容
	 */
	FStatement findFStatByFSId(String fsId);

	/**
	 * 动态更新结算单
	 */
	boolean updateSelectiveFs(FStatement fStatement);

	/**
	 * 确认结算单是否结算完毕
	 */
	boolean confrimFs(FStatement fStatement);

	/**
	 * 修改结算单
	 */
	boolean editFStatement(FStatement fStatement);

}
