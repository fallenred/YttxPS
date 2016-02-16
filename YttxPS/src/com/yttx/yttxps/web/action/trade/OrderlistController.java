package com.yttx.yttxps.web.action.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.vo.OrderlistRequest;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("orderlist/")
public class OrderlistController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IOrderlistService orderlistService;
	
	/**
	 * 分页查询订单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTicket(OrderlistRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getOrderlist());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyOrderlist(map);
		List<TOrderlistWithBLOBs> list = orderlistService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新增订单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="addOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTicket(TOrderlistWithBLOBs orderlist)
    {  
		logger.debug("当前新增对象 {}", orderlist);
		try{
			orderlistService.insert(orderlist);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新订单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="editOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTicket(TOrderlistWithBLOBs orderlist)
    {  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			orderlistService.update(orderlist);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除订单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="delOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTicket(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = orderlistService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
