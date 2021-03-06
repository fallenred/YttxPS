package com.yttx.yttxps.web.action.member;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.CustomInfo;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.vo.CostomPageRequest;
import com.yttx.yttxps.service.IMemberService;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 会员管理模块--会员管理Controller
 */
@Controller
@Scope("prototype")
@RequestMapping("member/admin/")
public class MemberManageController extends BaseController {
	static Logger logger = LoggerFactory.getLogger(MemberManageController.class);
	
	@Autowired
	private IMemberService memberService;
	@Autowired
	private ISysService sysService;
	
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
	
	/**
	 * 根据id找到一个会员的详细信息
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="show.htm")
	public String  findCustomerById(@RequestParam("id") String id,
			Model model) throws UnsupportedEncodingException{
		id = new String(id.getBytes("ISO-8859-1"),"utf-8");
		CustomInfo customer = memberService.selectCusById(id);
		//查询销售人员姓名
		if (StringUtils.isNotEmpty(customer.getSalesManID())){
			SysOper oper = sysService.findOperById(customer.getSalesManID());
			if (oper != null) {
				customer.setSalesManID(oper.getSysOperName());
			}
		}
		model.addAttribute("cus", customer);
		return "member/show";
	}
	
	/**
	 * 修改客户状态 
	 */
	@RequestMapping(value="modCusStat")
	@ResponseBody
	public Object modCusStat(@RequestParam("id") String id,@RequestParam("oper") String oper ){
		CustomInfo customInfo = new CustomInfo();
		if("S".equalsIgnoreCase(oper)){
			customInfo.setStat(-100);
		}else if("A".equalsIgnoreCase(oper)){
			customInfo.setStat(1);
		}
		customInfo.setId(id);
		
		try {
			memberService.updateCusSelective(customInfo);
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		
		return JsonResult.jsonOk();
	}
	
}
