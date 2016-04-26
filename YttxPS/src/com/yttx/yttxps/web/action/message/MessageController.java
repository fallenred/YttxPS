package com.yttx.yttxps.web.action.message;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.Message;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.vo.CustChgAuRequest;
import com.yttx.yttxps.model.vo.MsgRequest;
import com.yttx.yttxps.model.vo.ScenicRequest;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.service.IScenicService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("message/")
public class MessageController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IMsgService msgService;
	
	
	/**
	 * 打开消息管理的界面
	 */
	@RequestMapping(value="page.htm")
	public String openPage(Model model){
		
		return "message/message";
	}
	
	
	/**
	 * 分页查询消息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findMsg.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindMsg(MsgRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getMessage());
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		map.put("recvId", sessionEntity.getId());
		req.copyPage(map);
		req.copyMessage(map);
		List<Message> list = msgService.selectSelectivePage(map);
		for(Message message : list){
			message.setMsgText(message.getMsgText().replaceAll("<!\\[CDATA\\[(.*)\\]\\]>", "$1"));
		}
		map.put("rows", list);
		return map;
    }
	
	
	/**
	 * 分页查询消息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findNewMsg.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindNewMsg(MsgRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getMessage());
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		map.put("recvId", sessionEntity.getId());
		req.copyPage(map);
		req.copyMessage(map);
		List<Message> list = msgService.selectNewMsg(map);
		for(Message message : list){
			message.setMsgText(message.getMsgText().replaceAll("<!\\[CDATA\\[(.*)\\]\\]>", "$1"));
		}
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 打开消息管理的界面
	 */
	@RequestMapping(value="show.htm")
	public String show(@RequestParam(value = "id") String  id,Model model){
		msgService.readMsg(id);
		return "message/show";
	}
	
}
