package com.yttx.yttxps.web.action.trade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.FStatementFilters;
import com.yttx.yttxps.model.OrderFilters;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;
import com.yttx.yttxps.model.vo.FStatementPageRequest;
import com.yttx.yttxps.model.vo.OrderPageRequest;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.service.IPayConfirService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 类描述：支付确认Controller
 * @author huangtao
 * @date 2016-04-13
 */
@Controller
@Scope("prototype")
@RequestMapping("payConfir/")
public class PayConfirController extends BaseController {
	
	static Logger logger = LoggerFactory.getLogger(PayConfirController.class);
	
	@Autowired
	private IPayConfirService payConfirService;
	
	@Autowired
	private IFStatementService fStatementService;
	
	@Autowired
	private IMsgService msgService;
	
	/**
	 * 打开结算单管理的界面
	 */
	@RequestMapping(value="page.htm")
	public String openPage(Model model, String orderID, String closeID, String msgid){
		if(StringUtils.isNoneEmpty(orderID)){
			model.addAttribute("orderID", orderID);
		} else if (StringUtils.isNoneEmpty(closeID)){
			model.addAttribute("closeID", closeID);
		}
		if(StringUtils.isNoneEmpty(msgid)){
			msgService.readMsg(msgid);
		}
		//将订单状态发送到web端
		Object order_stat_item = getDictMapJsonByParentNo("order_stat");
		model.addAttribute("order_stat_item",order_stat_item);
		//将结算单状态发送到web端
		List<Dict> fsmt_stat_list = getDictListByParentNo("fsmt_stat");
		Object fsmt_stat_item = getDictMapJsonByParentNo("fsmt_stat");
		model.addAttribute("fsmt_stat_list",fsmt_stat_list);
		model.addAttribute("fsmt_stat_item",fsmt_stat_item);
		return "payConfir/corderlist";
	}
	
	/**
	 * 查询需要确认支付的订单
	 */
	@RequestMapping(value="simplelist.htm")
	@ResponseBody
	public Object findSimpleOrderList(OrderPageRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrderFilters orderFilters = req.getFilters();
		orderFilters.setStat(6);
		
		req.copyPage(map);
		req.copyOrderFilters(map);
		List<SimpleOrder> list =null;
		try {
			list = payConfirService.selectSelectivePage(map);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return map;	
	}
	
	/**
	 * 打开订单支付确认页面
	 */
	@RequestMapping(value="showOrder.htm")
	public String openOrderPage(@RequestParam("orderId") String orderId,
			Model model){
		Map<String, String> zy_map = getDictMapByParentNo("zy");
		model.addAttribute("zy_map", zy_map);
		DetailOrder dOrder = payConfirService.findOrderDetail(orderId);
		logger.debug("orderId为"+orderId+"的详细信息：{}",dOrder);
		model.addAttribute("order", dOrder);
		return "payConfir/orderInfo";
	}
	
	/**
	 * 打开结算单支付确认页面
	 */
	@RequestMapping(value="showStatement.htm")
	public String openStatementPage(@RequestParam("fsId") String fsId,
			Model model){
		Map<String, String> zy_map = getDictMapByParentNo("zy");
		model.addAttribute("zy_map", zy_map);
		FStatement statement = fStatementService.findFStatByFSId(fsId);
		logger.debug("statementId为"+fsId+"的详细信息：{}", statement);
		model.addAttribute("statement", statement);
		return "payConfir/statementInfo";
	}
	
	/**
	 * 订单确认支付
	 * @param orderId
	 * @param paidAmt 支付金额
	 * @return
	 */
	@RequestMapping(value="orderConfir.htm")
	@ResponseBody
	public Object ajaxOrderConfir(@RequestParam("orderId") String orderId, BigDecimal paidAmt){
		TOrderlist order = new TOrderlistWithBLOBs();
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		String operid = sessionEntity.getId();
		try {
			order.setFsNo(orderId);
			order.setFdPaidamt(paidAmt);
			order.setFiStat("8");
			payConfirService.orderConfir(order, operid);
			order.setFsOperId(sessionEntity.getId());
			this.msgService.saveMsg(order, sessionEntity.getId(),"6");
			logger.debug("orderId为"+orderId+"的详细信息：{}", order);
		} catch (Exception e) {
			logger.error("订单支付失败，订单id："+orderId, e);
			return JsonResult.jsonError("订单支付确认失败");
		}
		return JsonResult.jsonData(order);
	}
	
	/**
	 * 结算单支付确认
	 * @param fsId
	 * @param totalFee 预估金额
	 * @param paidAmt 支付金额
	 * @param amt 双方需交易金额
	 * @return
	 */
	@RequestMapping(value="statementConfir.htm")
	@ResponseBody
	public Object ajaxStatementConfir(@RequestParam("fsId") String fsId, BigDecimal totalFee, BigDecimal paidAmt, BigDecimal amt){
		FStatement statement = new FStatement();
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		String operid = sessionEntity.getId();
		try {
			statement.setStatmentId(fsId);
			statement.setPaidAmt(paidAmt.add(amt));
			statement.setAmt(totalFee.subtract(statement.getPaidAmt()));
			statement.setStat(1l);
			payConfirService.statementConfir(statement, operid);
			
			statement.setOperId(sessionEntity.getId());
			this.msgService.saveMsg(statement, sessionEntity.getId(), "-1");
		} catch (Exception e) {
			logger.error("结算单支付失败，结算单id："+fsId, e);
			return JsonResult.jsonError("订单支付确认失败");
		}
		return JsonResult.jsonData(statement);
	}
	
	/**
	 * ajax获取订单详细信息
	 */
	@RequestMapping(value="detailOrder.htm")
	@ResponseBody
	public Object findOrderDetail(@RequestParam("orderId") String orderId){
		DetailOrder dOrder = null;
		try {
			dOrder = payConfirService.findOrderDetail(orderId);
			logger.debug("orderId为"+orderId+"的详细信息：{}",dOrder);
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonData(dOrder);
	}	
	
	/**
	 * ajax获取结算单列表
	 */
	@RequestMapping(value="FSList.htm")
	@ResponseBody
	public Object findFSList(FStatementPageRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		request.copyPage(map);
		request.copyFStatementFilters(map);
		map.put("stat", "-1");
		List<FStatement> list = fStatementService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}	
	
}
