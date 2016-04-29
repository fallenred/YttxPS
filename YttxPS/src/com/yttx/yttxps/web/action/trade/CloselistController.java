package com.yttx.yttxps.web.action.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.TCloselist;
import com.yttx.yttxps.model.vo.CloselistRequest;
import com.yttx.yttxps.service.ICloselistService;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("closelist/")
public class CloselistController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ICloselistService closelistService;
	
	@Autowired
	private IMsgService msgService;
	
	/**
	 * 分页查询结算单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findCloselist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTicket(CloselistRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getCloselist());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyOrderlist(map);
		List<TCloselist> list = closelistService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新增结算单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="addCloselist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTicket(TCloselist closelist)
    {  
		logger.debug("当前新增对象 {}", closelist);
		try{
			closelistService.insert(closelist);
			HttpSession session = request.getSession();
			SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
			closelist.setFsOperId(sessionEntity.getId());
			this.msgService.saveMsg(closelist, sessionEntity.getId(),"");
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新结算单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="editCloselist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTicket(TCloselist orderlist)
    {  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			closelistService.update(orderlist);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除结算单信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="delCloselist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTicket(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = closelistService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
