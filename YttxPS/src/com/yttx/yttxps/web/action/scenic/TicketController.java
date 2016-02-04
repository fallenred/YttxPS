package com.yttx.yttxps.web.action.scenic;

import java.util.Date;
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
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;
import com.yttx.yttxps.model.vo.GenRequest;
import com.yttx.yttxps.model.vo.TicketRequest;
import com.yttx.yttxps.service.ITicketService;
import com.yttx.yttxps.web.action.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("ticket/")
public class TicketController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private ITicketService ticketService;
	
	/**
	 * 分页查询门票信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findTicket.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTicket(TicketRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getTicket());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTicket(map);
		List<Tticket> list = ticketService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 分页查询门票价格信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findTicketPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTicketPrice(TicketRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getTicket());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTicket(map);
		List<Tticket> list = ticketService.selectTicketPricePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取门票列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectTicket.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectGen(GenRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getGen());
		TticketExample example = new TticketExample();
		List<Tticket> list = ticketService.selectTticket(example);
		return list;
    }
	
	/**
	 * 新增门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="addTicket.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTicket(Tticket ticket)
    {  
		logger.debug("当前新增对象 {}", ticket);
		try{
			ticketService.insert(ticket);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 新增门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="addTicketPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTicketPrice(Tticket ticket)
    {  
		logger.debug("当前新增对象 {}", ticket);
		try{
			ticketService.insertTicketPrice(ticket);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="editTicket.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTicket(Tticket ticket)
    {  
		logger.debug("当前更新对象 {}", ticket);
		try{
			ticketService.update(ticket);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="editTicketPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTicketPrice(Tticket ticket)
    {  
		logger.debug("当前更新对象 {}", ticket);
		try{
			ticketService.updateTicketPrice(ticket);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="delTicket.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTicket(@RequestParam(value = "no") String  no, Date ftStartdate, Date ftEnddate)
    {  
		logger.debug("当前删除key {}", no);
		try{
			ticketService.delete(no);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除门票信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="delTicketPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTicketPrice(TCCPrice tccPrice)
    {  
		logger.debug("当前删除key {}", tccPrice);
		try{
			ticketService.deleteTicketPrice(tccPrice);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
