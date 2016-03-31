package com.yttx.yttxps.web.action.cOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.OrderFilters;
import com.yttx.yttxps.model.corder.DetailOrder;
import com.yttx.yttxps.model.corder.FStatement;
import com.yttx.yttxps.model.corder.ORemark;
import com.yttx.yttxps.model.corder.SimpleOrder;
import com.yttx.yttxps.model.vo.FStatementPageRequest;
import com.yttx.yttxps.model.vo.OrderPageRequest;
import com.yttx.yttxps.service.IClearOrderService;
import com.yttx.yttxps.service.IFStatementService;
import com.yttx.yttxps.web.action.BaseController;

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
	
	/**
	 * 打开结算单管理的界面
	 */
	@RequestMapping(value="page.htm")
	public String openPage(Model model){
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
	 */
	@RequestMapping(value="prodStat.htm")
	public String openProdFStatPage(@RequestParam("orderId") String orderId,
			Model model)
	{
		
		Map<String, String> zy_map = getDictMapByParentNo("zy");
		model.addAttribute("zy_map", zy_map);
		
		//订单的详细信息
		DetailOrder dOrder = clearOrderService.findOrderDetail(orderId);
		
		//生成结算单对象
		FStatement fs = new FStatement();
		String routeId =dOrder.getRouteId();
		fs.setRouteID(routeId);//设置线路ID
		
		if(!StringUtil.nullOrBlank(routeId)){
			String routeName = clearOrderService.findRouteName(routeId.trim());
			fs.setRouteName(routeName);//线路名称
		}
		fs.setStatmentName(dOrder.getName());//结算单名称
		fs.setOrderId(dOrder.getNo());//	订单ID
		fs.setUserID(dOrder.getUserId());
		fs.setUserSubID(dOrder.getUserSubId());
		fs.setOrderTotolFee(dOrder.getTotalFee());
		
		List<ORemark> remarks =dOrder.getRemarks();
		BigDecimal remarkAmt =new BigDecimal(0);
		BigDecimal remarkPaidAmt = new BigDecimal(0);
		if(remarks!=null){
			for(ORemark remark:remarks){
				remarkAmt=add(remarkAmt,remark.getAmt());
				remarkPaidAmt = add(remarkPaidAmt, remark.getPaidAmt());
			}
		}
		fs.setRemarksAmt(remarkAmt);
		fs.setTotalFee(add(dOrder.getTotalFee(),remarkAmt));
		fs.setPaidAmt(add(dOrder.getPaidAmt(), remarkPaidAmt));
		fs.setAmt(add(fs.getTotalFee(),fs.getPaidAmt().negate()));
		model.addAttribute("oper", "A");
		model.addAttribute("order", dOrder);
		model.addAttribute("fStat",fs);
		return "cOrder/preStatment";	//返回欲生成结算单页面
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
			
			if(exitFS==null){
				try {
					String statId=clearOrderService.addFStatement(fStatement);
					message="结算单生成成功，"+
					"单号<a href='/cOrder/showFS.htm?fsId="+statId+"'>"+statId+"</a>";
				} catch (Exception e) {
					message="新增结算单失败,请联系管理员!";
					logger.error(e.getMessage());
				}
			}else{
				message ="该订单已生成结算单";
			}
		}else if("E".equalsIgnoreCase(oper)){//修改操作
			try{
				fStatement.setCreatDate(new Date());
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
