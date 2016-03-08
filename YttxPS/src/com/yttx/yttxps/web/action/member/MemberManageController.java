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

import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.vo.CostomPageRequest;
import com.yttx.yttxps.service.IMemberService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 会员管理模块--会员管理Controller
 */
@Controller
@Scope("prototype")
@RequestMapping("member/")
public class MemberManageController extends BaseController {
	static Logger logger = LoggerFactory.getLogger(MemberManageController.class);
	
	@Autowired
	private IMemberService memberService;
	
	/**
	 * 打开会员管理界面
	 */
	@RequestMapping(value="page.htm")
	public String openManagePage(){
		return "member/manage";
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
		List<CustomInfo> list =	memberService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}	
}
