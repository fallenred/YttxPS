package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.xml.bean.closeList.Root;
import com.yttx.yttxps.xml.bean.closeList.Shop;

/**
 * 类描述：结算单service
 * @author sunchao
 * @date 2016年2月26日 上午11:10:50
 */
public interface IFStatementService {

	/**
	 * 分页查找结算单的list
	 */
	@Transactional(readOnly = true) 
	List<FStatement> selectSelectivePage(Map<String, Object> map);

	/**
	 * 通过结算单号找到结算单内容
	 */
	@Transactional(readOnly = true) 
	FStatement findFStatByFSId(String fsId);

	/**
	 * 动态更新结算单
	 */
	@Transactional
	boolean updateSelectiveFs(FStatement fStatement);

	/**
	 * 确认结算单是否结算完毕
	 */
	@Transactional
	boolean confrimFs(FStatement fStatement);

	/**
	 * 修改结算单
	 */
	@Transactional
	boolean editFStatement(FStatement fStatement);

	/**
	 * 通过订单号查询结算单
	 * @param orderid
	 * @return
	 */
	FStatement findFStatByOrderid(String orderid);
	
	/**
	 * 新增购物店Reslist
	 * @param shop 返回保存后的所有购物信息
	 * @param orderid
	 * @return
	 */
	Shop addShopReslist(Shop shop, String orderid);

	/**
	 * 删除购物店Reslist
	 * @param orderid
	 * @param resno
	 * @throws Exception 
	 */
	Shop delShopReslist(String orderid, String resno) throws Exception;

	/**
	 * 保存结算单
	 * @param root 协议
	 * @param fStatement 结算单
	 * @throws Exception 
	 */
	void saveCloselist(Root root, FStatement fStatement) throws Exception;
}
