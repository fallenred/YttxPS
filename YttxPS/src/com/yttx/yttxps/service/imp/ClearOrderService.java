package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.ClearOrderMapper;
import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;
import com.yttx.yttxps.service.IClearOrderService;
import com.yttx.yttxps.service.IPubService;

/**
 * 类描述：结算单模块的Service
 * @author sunchao
 * @date 2016年2月23日 下午5:23:16
 */
@Service("clearOrderService")
public class ClearOrderService implements IClearOrderService{
	
	@Autowired
	private IPubService<SimpleOrder> pubService;
	
	@Autowired
	private ClearOrderMapper<SimpleOrder> clearOrderMapper;
	
	@Autowired
	private FStatementMapper< FStatement>  fStatementMapper;
	
	/**
	 * 分页查询简单订单列表
	 */
	@Override
	public List<SimpleOrder> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, clearOrderMapper);
	}

	/**
	 * 通过订单唯一索引（orderId）找到订单的详细信息
	 */
	@Override
	public DetailOrder findOrderDetail(String orderId) {
		return clearOrderMapper.findOrderDetail(orderId);
	}

	/**
	 * 通过routeId找到routeName
	 */
	@Override
	public String findRouteName(String routeId) {
		return clearOrderMapper.findRouteNameById(routeId);
	}

	/**
	 * 通过orderId找到结算单
	 */
	@Override
	public FStatement findFStatByOrderId(String orderId) {
		return fStatementMapper.selectFSByOrderId(orderId);
	}

	/**
	 * 新增一个结算单
	 */
	@Override
	public String addFStatement(FStatement fStatement){
		fStatement.setStatmentId(fStatement.getOrderId());
		fStatementMapper.insert(fStatement);
		clearOrderMapper.updateStatById(fStatement.getOrderId());
		return fStatement.getStatmentId();
	}
	/**
	 * 从序列中生成一个id号
	 */
	private String generateFSId(){
		String id =""+fStatementMapper.generateFSId();
		int len=10-id.length();
		for(int i=0;i<len;i++){
			id="0"+id;
		}
		return id.toString();
	}
}
