package com.yttx.yttxps.web.action.cOrder;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.except.BusinessException;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.OrderFilters;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.SimpleOrder;
import com.yttx.yttxps.model.vo.FStatementPageRequest;
import com.yttx.yttxps.model.vo.OrderPageRequest;
import com.yttx.yttxps.service.IClearOrderService;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.closeList.Reslist;
import com.yttx.yttxps.xml.bean.closeList.Root;
import com.yttx.yttxps.xml.bean.closeList.Shop;

/**
 * 类描述：结算管理的Controller
 * @author sunchao
 * @date 2016年2月22日 下午12:55:01
 */
@Controller
@Scope("prototype")
@RequestMapping("cOrder/")
public class StatementController extends BaseController {
	
	static Logger logger = LoggerFactory.getLogger(StatementController.class);
	
	@Autowired
	private  IClearOrderService clearOrderService;
	
	@Autowired
	private  IFStatementService fStatementService;
	
	@Autowired
	private IMsgService msgService;
	
	/**
	 * 打开结算单管理的界面
	 */
	@RequestMapping(value="page.htm")
	public String openPage(Model model,String orderID,String closeID,String msgid){
		if(StringUtils.isNoneEmpty(msgid)){
			msgService.readMsg(msgid);
		}
		if(StringUtils.isNoneEmpty(orderID)){
			model.addAttribute("orderID",orderID);
		} else if (StringUtils.isNoneEmpty(closeID)){
			FStatement fStatement = fStatementService.findFStatByFSId(closeID);
			model.addAttribute("orderID",fStatement.getOrderId());
			model.addAttribute("statement_stat",fStatement.getStat());
		}
		//将结算单状态发送到web端
		List<Dict> fsmt_stat_list = getDictListByParentNo("fsmt_stat");
		Object fsmt_stat_item = getDictMapJsonByParentNo("fsmt_stat");
		model.addAttribute("fsmt_stat_list",fsmt_stat_list);
		model.addAttribute("fsmt_stat_item",fsmt_stat_item);
		
		//将订单状态发送到web端
		Object order_stat_item = getDictMapJsonByParentNo("order_stat");
		model.addAttribute("order_stat_item",order_stat_item);
		
		return "cOrder/corderlist";
	}
	
	/**
	 * 打开结算单页面
	 */
	@RequestMapping(value="showCloselist.htm")
	public String openCloselist(Model model, String orderid){
		FStatement fStatement = fStatementService.findFStatByOrderid(orderid);
		try{
			Root root = ResScheduleXMLConverter.fromXml("www.yttx.co", fStatement.getOrderContent(), Root.class);
			model.addAttribute("content", root.getBody());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("结算单xml转bean错误", e);
		}
		model.addAttribute("fStatement",fStatement);
		return "orderlist/closelist";
	}
	
	/**
	 * 保存结算单
	 * @param orderid
	 * @param root
	 * @param stat
	 * @return
	 */
	@RequestMapping(value="saveCloselist.htm")
	@ResponseBody
	public Object saveCloselist(String orderid, Root root, String stat){
		try{
			this.fStatementService.saveCloselist(root, orderid, stat);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 保存购物店信息
	 */
	@RequestMapping(value="addShopReslist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object addShopReslist(Shop shop, String orderid){
		try{
			shop = this.fStatementService.addShopReslist(shop, orderid);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		result.put("shopInfo", shop);
		return result;
	}
	
	/**
	 * 查询购物店信息
	 */
	@RequestMapping(value="getShopReslist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object getShopReslist(String orderid, String resno){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			FStatement fStatement = fStatementService.findFStatByOrderid(orderid);
			Root root = ResScheduleXMLConverter.fromXml("www.yttx.co", fStatement.getOrderContent(), Root.class);
			Shop shop = root.getBody().getIncomedetails().getShop();
			int length = shop.getReslist().size();
			for (int i = 0; i < length; i++) {
				Reslist reslist = shop.getReslist().get(i);
				if (resno.equals(reslist.getResno())) {
					result.put("result", "ok");
					result.put("reslist", reslist);
					return result;
				}
			}
		}catch(Exception e){
			logger.error("查询购物信息错误", e);
			return JsonResult.jsonError(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除购物店信息
	 */
	@RequestMapping(value="delShopReslist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object delShopReslist(String orderid, String resno){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Shop shop = this.fStatementService.delShopReslist(orderid, resno);
			result.put("result", "ok");
			result.put("shopInfo", shop);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 分页获取简单订单对象列表
	 */
	@RequestMapping(value="simplelist.htm")
	@ResponseBody
	public Object findSimpleOrderList(OrderPageRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrderFilters orderFilters = req.getFilters();
		orderFilters.setStat(8);
		
		//是否要过滤相应的计调
		//orderFilters.setOperId(sessionEntity.getId());
		req.copyPage(map);
		req.copyOrderFilters(map);
		List<SimpleOrder> list =null;
		try {
			list = clearOrderService.selectSelectivePage(map);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return map;	
	}
	
	/**
	 * 打开订单页面
	 */
	@RequestMapping(value="showOrder.htm")
	public String openOrderPage(@RequestParam("orderId") String orderId,
			Model model)
	{
		Map<String, String> zy_map = getDictMapByParentNo("zy");
		model.addAttribute("zy_map", zy_map);
		DetailOrder dOrder = clearOrderService.findOrderDetail(orderId);
		logger.debug("orderId为"+orderId+"的详细信息：{}",dOrder);
		model.addAttribute("order", dOrder);
		return "cOrder/orderInfo";	
	}
	
	/**
	 * ajax获取订单详细信息
	 */
	@RequestMapping(value="detailOrder.htm")
	@ResponseBody
	public Object findOrderDetail(@RequestParam("orderId") String orderId){
		DetailOrder dOrder = null;
		try {
			dOrder = clearOrderService.findOrderDetail(orderId);
			logger.debug("orderId为"+orderId+"的详细信息：{}",dOrder);
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonData(dOrder);
	}	
	
	/**
	 * 打开生成结算单页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="prodStat.htm")
	public String openProdFStatPage(String taname, String orderId, Model model) throws UnsupportedEncodingException {
		DetailOrder detailOrder = this.clearOrderService.findOrderDetail(orderId);
		com.yttx.yttxps.xml.bean.Root root = ResScheduleXMLConverter.fromXml("www.yttx.co", detailOrder.getSchedule(), com.yttx.yttxps.xml.bean.Root.class);
		model.addAttribute("adult", root.getBody().getAdult());//成人
		model.addAttribute("children", root.getBody().getChildren());//儿童
		model.addAttribute("fullguide", root.getBody().getFullguide());//全陪
		model.addAttribute("order", detailOrder);	//订单详情
		model.addAttribute("taname", new String(taname.getBytes("ISO-8859-1"),"utf-8"));
		return "cOrder/preStatment";				//返回欲生成结算单页面
	}
	
	/**
	 * 打开生成结算单页面
	 */
	@RequestMapping(value="openEditFS.htm")
	public String openEditFStatPage(@RequestParam("fsId") String fsId,
			Model model)
	{
		//获取结算单对象
		FStatement fStatement= fStatementService.findFStatByFSId(fsId);
		model.addAttribute("fStat",fStatement);
		model.addAttribute("oper","E");
		return "cOrder/preStatment";	//返回欲生成结算单页面
	}
	
	
	/**
	 * 打开生成结算单页面
	 */
	@RequestMapping(value="submitStat.htm")
	public String submitFStatement(FStatement fStatement,
			@RequestParam("oper")String oper,
			Model model)
	{
		String message=null;
		if("A".equalsIgnoreCase(oper)){//新增操作
			fStatement.setOperId(sessionEntity.getId());
			fStatement.setStat(0L);
			String orderId = fStatement.getOrderId();
			FStatement exitFS = clearOrderService.findFStatByOrderId(orderId);
			
			HttpSession session = request.getSession();
			SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
			if(exitFS==null){
				try {
					String statId=clearOrderService.addFStatement(fStatement);
					message="结算单生成成功，"+
					"单号<a href='/cOrder/showFS.htm?fsId="+statId+"'>"+statId+"</a>";
					this.msgService.saveMsg(fStatement, sessionEntity.getId(),"");
				} catch (BusinessException e) {
					logger.error("消息生成失败："+"\n" + e.getMessage(), e);
				} catch (Exception e) {
					message="新增结算单失败,请联系管理员!";
					logger.error(e.getMessage());
				}
			}else{
				message ="该订单已生成结算单";
			}
		}else if("E".equalsIgnoreCase(oper)){//修改操作
			try{
				fStatementService.editFStatement(fStatement);
				message="该结算单更新成功";
			}catch (Exception e) {
					message=e.getMessage();
			}
		}
		model.addAttribute("message", message);
		return "cOrder/message";	//返回欲生成结算单页面
	}
	
	/**
	 * ajax获取结算单列表
	 */
	@RequestMapping(value="FSList.htm")
	@ResponseBody
	public Object findFSList(FStatementPageRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//是否要过滤相应的计调
//		FStatementFilters filters =request.getFilters();
//		filters.setOperId(sessionEntity.getCurrId());
		request.copyPage(map);
		request.copyFStatementFilters(map);
		List<FStatement> list = fStatementService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}	
	
	/**
	 * 打开结算单界面
	 */
	@RequestMapping(value="showFS.htm")
	public String showFS(@RequestParam("fsId") String fsId,Model model){
		FStatement fStatement = fStatementService.findFStatByFSId(fsId);
		model.addAttribute("fs", fStatement);
		return "cOrder/fStatement";
	}
	
	
	/**
	 * 打开结算单界面
	 */
	@RequestMapping(value="confirmFS.htm")
	@ResponseBody
	public Object  confrimFS(@RequestParam("fsId") String fsId,Model model){
		FStatement fStatement = new FStatement();
		fStatement.setStatmentId(fsId);
		fStatement.setStat(2L);
		try{
			fStatementService.confrimFs(fStatement);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	
	
	/*
	 * 两个BigDecimal的数相加保留两位小数
	 */
	private BigDecimal add(BigDecimal decimal1,BigDecimal decimal2){
		return decimal1.add(decimal2).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
}
