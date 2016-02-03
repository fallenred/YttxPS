package com.yttx.yttxps.web.action.member;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.CustChgAu;
import com.yttx.yttxps.model.vo.CustChgAuRequest;
import com.yttx.yttxps.service.ICustChgAuService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("member/")
public class MemberController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ICustChgAuService custChgAuService;
	
	/**
	 * 分页查询待审核会员信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findCustChgAu.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenic(CustChgAuRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getCustchgau());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyCustChgAu(map);
		List<CustChgAu> list = custChgAuService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 更新审批信息
	 * @param custChgAu
	 * @return
	 */
	@RequestMapping(value="editCustChgAu.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditCustChgAu(CustChgAu custChgAu)
    {  
		logger.debug("当前更新对象 {}", custChgAu);
		try{
			int ret = custChgAuService.update(custChgAu);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
