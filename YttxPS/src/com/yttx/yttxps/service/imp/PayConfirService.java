package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.except.BusinessException;
import com.yttx.yttxps.mapper.ClearOrderMapper;
import com.yttx.yttxps.mapper.FStatementMapper;
import com.yttx.yttxps.mapper.TCloselistMapper;
import com.yttx.yttxps.mapper.TOrderlistMapper;
import com.yttx.yttxps.mapper.TrlistMapper;
import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.Trlist;
import com.yttx.yttxps.model.TrlistExample;
import com.yttx.yttxps.model.TrlistExample.Criteria;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;
import com.yttx.yttxps.service.IPayConfirService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Root;

@Service("payConfirService")
public class PayConfirService implements IPayConfirService{
	
	@Autowired
	private IPubService<SimpleOrder> pubService;
	
	@Autowired
	private ClearOrderMapper<SimpleOrder> clearOrderMapper;
	
	@Autowired
	private TOrderlistMapper<TOrderlist> orderlistMapper;
	
	@Autowired
	private TCloselistMapper<TCloselist> closelistMapper;
	
	@Autowired
	private TrlistMapper<Trlist> trlistMapper;
	
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


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void orderConfir(TOrderlist order, String operId){
		orderlistMapper.updateByPrimaryKeySelective((TOrderlistWithBLOBs)order);
		//查询订单信息
		TrlistExample example = new TrlistExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsOrderIdEqualTo(order.getFsNo());
		criteria.andFsTrcodeEqualTo("1002");
		criteria.andFiTrtypeEqualTo(BigDecimal.ONE);
		criteria.andFsTrchannelEqualTo("06");
		List<Trlist> list = trlistMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list) || list.size() > 1) {
			throw new BusinessException("流水信息不存在，订单支付无法确认");
		}
		
		Trlist trlist = list.get(0);
		trlist.setFsSysoperid(operId);
		trlist.setFdAmt(order.getFdPaidamt());
		trlist.setFiStat(BigDecimal.ONE);
		trlistMapper.updateByPrimaryKeySelective(trlist);
		
		TCloselist closeList = closelistMapper.selectByPrimaryKey(order.getFsNo());
		closeList.setFdPaidamt(order.getFdPaidamt());
		closeList.setFdAmt(closeList.getFdTotalfee().subtract(order.getFdPaidamt()));
		closelistMapper.updateByPrimaryKeySelective(closeList);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void statementConfir(FStatement statement, String operId){
		fStatementMapper.updateFSSelective(statement);
		//查询订单信息
		TrlistExample example = new TrlistExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsCloseIdEqualTo(String.format("%-20s", statement.getStatmentId()));
		criteria.andFsTrcodeEqualTo("1004");
		//criteria.andFiTrtypeEqualTo(BigDecimal.ONE);//支付状态
		criteria.andFsTrchannelEqualTo("06");
		List<Trlist> list = trlistMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list) || list.size() > 1) {
			throw new BusinessException("流水信息不存在，订单支付无法确认");
		}
		
		Trlist trlist = list.get(0);
		trlist.setFsSysoperid(operId);
		trlist.setFdAmt(statement.getAmt());
		trlist.setFiStat(BigDecimal.ONE);
		trlistMapper.updateByPrimaryKeySelective(trlist);
		this.fStatementMapper.updateFSSelective(statement);
	}
	
	/**
	 * 通过orderId找到结算单
	 */
	@Override
	public FStatement findFStatByOrderId(String orderId) {
		return fStatementMapper.selectFSByOrderId(orderId);
	}
}
