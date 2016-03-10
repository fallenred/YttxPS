package com.yttx.yttxps.web.action.member;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.vo.CostomPageRequest;
import com.yttx.yttxps.service.IMemberAuditService;
import com.yttx.yttxps.service.IMemberService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 会员管理模块--会员审核Controller
 */
@Controller
@Scope("prototype")
@RequestMapping("member/audit/")
public class MemberAuditController extends BaseController {
	static Logger logger = LoggerFactory.getLogger(MemberAuditController.class);
	
	@Autowired 
	private IMemberService memberService;
	
	@Autowired
	private IMemberAuditService memberAuditService;
	
	/**
	 * 打开会员管理界面
	 */
	@RequestMapping(value="page.htm")
	public String openManagePage(){
		return "member/auditpage";
	}
	
	/**
	 * 分页加载数据
	 */
	@RequestMapping(value="listpage.htm",method = RequestMethod.POST)
	@ResponseBody
	public Object findPageList(CostomPageRequest requst){
		Map<String,Object> map = new HashMap<String,Object>();
		requst.copyPage(map);
		requst.copyCustomFilters(map);
		List<CustomInfo> list =	memberAuditService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}	
	
	/**
	 * 打开会员审核页面
	 */	
	@RequestMapping(value="auditpage.htm")
	public String  findCustomerById(@RequestParam("id") String id,
			@RequestParam("auditNo") String auditNo,
			@RequestParam("auditType") BigDecimal auditType,
			Model model){
		model.addAttribute("auditType", auditType);
		CustomInfo auditCus = memberAuditService.selectCusByAuditNo(auditNo);//找到需要审核的信息
		model.addAttribute("acus", auditCus);
		if(auditType.intValue()==2){//如果是信息变更，找到客户的原始信息
			CustomInfo ordCus = memberService.selectCusById(id);
			model.addAttribute("ocus", ordCus);
		}
		return "member/audit";
	}
	
	/**
	 * 会员审核
	 */
	@RequestMapping(value="audit.htm")
	@ResponseBody
	public Object auditCustomer(CustomInfo req){
		String auditNo = req.getAuditno();
		CustomInfo auditRec = memberAuditService.selectCusByAuditNo(auditNo);
		if(auditRec.getAuditRet().intValue()!=0){//首先判断该会员是否已经审核过了
			return JsonResult.jsonError("该会员已审核");
		}
		
		auditRec.setAuditRet(req.getAuditRet());//审核结果
		auditRec.setAuditor(sessionEntity.getId());//审核人
		auditRec.setAuditTime(new Date());//审核时间
		auditRec.setComment(req.getComment());//审核意见
		if(!StringUtil.nullOrBlank(req.getSalesManID()))
			auditRec.setSalesManID(req.getSalesManID());//销售人员id
		try {
			memberAuditService.auditCustomer(auditRec);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
}
