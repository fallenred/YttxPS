package com.yttx.yttxps.web.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.model.vo.SysOperRequest;
import com.yttx.yttxps.model.vo.SysOperSubRequest;

/**
 * 类描述：操作员控制类
 * @author suncao
 * @date 2016年2月1日 下午4:18:19
 */
@Controller
@Scope("prototype")
@RequestMapping("user/")
public class OperController extends UserBasicController{
	static Logger logger = LoggerFactory.getLogger(OperController.class);

	/**
	 * 打开操作员管理页面
	 * @return String 
	 */
	@RequestMapping(value="opersetting.htm")
	public ModelAndView openOperPage(Model model){
		
		//向页面输入当前操作员信息
		String sysOperId = sessionEntity.getId();
		SysOper oper = sysService.findOperById(sysOperId);
		model.addAttribute("oper", oper);
		
		//向页面输入部门信息
		List<SysDep> deps =sysService.findDepsByStat(null);
		model.addAttribute("deps", deps);
		
		return new ModelAndView("oper/oper");	
	}
	
	
	/**
	 * 分页查询操作员信息
	 * @param SysOperRequest
	 * @return Object
	 */
	@RequestMapping(value="oper/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object listSysOper(SysOperRequest req)
    {  
		logger.debug("当前查询条件 {}", req);
		String sysOperId = sessionEntity.getId();
		SysOper oper = sysService.findOperById(sysOperId);
		Long  adminType = oper.getAdminType();
		if(adminType != 1){
			req.getSysOper().setDepNo(oper.getDepNo());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copySysOper(map);
		List<SysOper> list = sysService.selectOperSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查看操作员详细信息
	 */
	@RequestMapping(value="oper/show.htm")
	public ModelAndView showOper(@RequestParam(value="sysOperId") String sysOperId)
    {  
		if(StringUtil.nullOrBlank(sysOperId)){
			JsonResult.jsonError("操作员id为空");
		}
		Map<String, Object> operDetailInfo = operDetailInfo(sysOperId);
		
		List<SysDep> deps = findDepsByStat(null);
		operDetailInfo.put("deps", deps);
		return new ModelAndView("oper/show",operDetailInfo);
    }
	
	
	/**
	 * 打开新增操作员界面
	 */
	@RequestMapping(value="oper/addpage.htm")
	public String openOperAddpage(Model model){
		//两种不同的类型：一种是超级管理员，一种是部门管理员 adminType=1/adminType=2
		//向页面输入当前操作员信息
		String sysOperId = sessionEntity.getId();
		SysOper oper = sysService.findOperById(sysOperId);
		model.addAttribute("oper", oper);
		
		//菜单或权限
		List<HashMap<String,Object>> menuList = new ArrayList<HashMap<String,Object>>();
		
		Long adminType = oper.getAdminType();
		if(adminType == 1){//如果是超级管理员
			menuList = superManagerRights();
			List<SysDep> deps = sysService.findDepsByStat(1L);//所有状态设置为正常的部门
			model.addAttribute("deps", deps);
		}
		if(adminType == 2){
			menuList = depManagerRights(sessionEntity.getDepNo());
		}
		model.addAttribute("menulist", menuList);
		return "oper/add";
	}
	
	
	/**
	 * 提交新增操作员数据
	 */
	@RequestMapping(value="oper/addsubmit.htm",method = RequestMethod.POST)
	@ResponseBody
	public Object addOper(SysOperSubRequest req){
		SysOper oper =req.getSysOper();
		oper.setPwdStat(0L);
		oper.setStat(1L);
		Map<String,Object> validMap = validSubData(req,"add");
		if((Boolean)validMap.get("succflag") == false){
			return JsonResult.jsonError((String)validMap.get("message"));
		}
		try{
			sysService.addSysOper(req);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}

	/**
	 * 打开修改操作员界面
	 */
	@RequestMapping(value="oper/editpage.htm")
	public String openOperEditpage(@RequestParam("sysOperId") String sysOperId,Model model){
		//向页面输出所有部门
		List<SysDep> deps = findDepsByStat(1L);
		model.addAttribute("deps", deps);
		
		//向页面输出用户信息
		SysOper oper = sysService.findOperById(sysOperId);
		model.addAttribute("operinfo", oper);
		
		String cOperId = sessionEntity.getId();
		SysOper cOper = sysService.findOperById(cOperId);
		model.addAttribute("cOper", cOper);
		
		//向页面输出用户权限树
		Long  adminType = currentAdminType();
		List<HashMap<String, Object>> allrights = null;
		if(adminType ==1){
			allrights = superManagerRights();
		}
		if(adminType == 2){
			allrights = depManagerRights(sessionEntity.getDepNo());
		}
		
		List<String> operRightIdList =operRightIdList(sysOperId);
		if(operRightIdList!=null){
			rightListChecked(allrights, operRightIdList);
		}
		model.addAttribute("allrights",allrights);		
		return "oper/edit";
	}
	
	/**
	 * 更新操作员数据
	 */
	@RequestMapping(value="oper/editsubmit.htm",method=RequestMethod.POST)
	@ResponseBody
	public Object updateOper(SysOperSubRequest req, @RequestParam("oldOperId") String oldOperId){
		String curOperId = sessionEntity.getId();
		Map<String,Object> validMap = validSubData(req,"edit");
		if((Boolean)validMap.get("succflag") == false){
			return JsonResult.jsonError((String)validMap.get("message"));
		}
		try{
			sysService.updateSysOperbyOperId(req,oldOperId,curOperId);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 注销操作员
	 */
	@RequestMapping(value="oper/cancel.htm",method = RequestMethod.POST)
	@ResponseBody
	public Object cancelOper(@RequestParam("sysOperId") String sysOperId){
		if(sysOperId == null){
			JsonResult.jsonError("缺少用户编号");
		}
		
		try{
			sysService.deleteOperByOperId(sysOperId);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 根据部门id找到部门下所有人
	 */
	@RequestMapping(value="oper/findOpersByDepNo.htm")
	@ResponseBody
	public Map<String, Object> findOpersByDepNo(Long key){
		List<SysOper> list = sysService.findOpersByDepNo(key);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succflag", 0);
		map.put("msg", null);
		map.put("data", list);
		return map;
	}
	
	//新增用户或修改用户信息，验证用户信息
	private Map<String, Object> validSubData(SysOperSubRequest req,String type) {
		Map<String,Object> flagMap = new HashMap<String,Object>();
		Boolean succFlag = true;
		String message = "";
		
		SysOper oper = req.getSysOper();
		if(oper == null){
			flagMap.put("succflag", false);
			flagMap.put("message", "未传入用户信息");
			return flagMap;
		}
		
		String operId = oper.getSysOperId();
		if(StringUtil.nullOrBlank(operId)||operId.length()<6||operId.length()>16){
			succFlag = succFlag && false;
			message+="用户编号为空/用户编号长度小于6或者大于16<br/>";
		}
		
		String operName = oper.getSysOperName();
		if(StringUtil.nullOrBlank(operName)||operName.length()>42){
			succFlag = succFlag && false;
			message+="用户名称为空/用户名称超长<br/>";
		}
		
		Long adminType = oper.getAdminType();
		if(adminType==null){
			succFlag = succFlag && false;
			message+="未选择管理身份<br/>";
		}
		
		Long depNo = oper.getDepNo();
		if(depNo==null){
			succFlag = succFlag && false;
			message+="未选择所属部门<br/>";
		}
		if("add".equalsIgnoreCase(type)){
			String pwd =oper.getSysOperPwd();
			if(StringUtil.nullOrBlank(pwd)||pwd.length()!=28){
				succFlag = succFlag && false;
				message +="密码为空/密码长度不对";
			}
		}
		
		flagMap.put("succflag", succFlag);
		flagMap.put("message", message);
		
		return flagMap;
	}
	
	//封装操作员详细信息
	private Map<String, Object> operDetailInfo(String sysOperId){
		Map<String, Object> model =new HashMap<String,Object>();
		//查找用户信息
		SysOper oper = sysService.findOperById(sysOperId);
		model.put("operinfo", oper);
		
		//查找用户权限
		List<HashMap<String, Object>> operRights=null;
		Long operAdminType=oper.getAdminType();
		if(operAdminType==1){
			operRights = superManagerRights();
		}else{
			operRights=operRights(sysOperId);
		}
		model.put("operRights", operRights);
		return model;
	}
	 
	
	//获取用户权限
	private List<HashMap<String, Object>> operRights(String sysOperId){
		List<HashMap<String, Object>> allRights = superManagerRights();
		List<HashMap<String, Object>> opeRight = new ArrayList<HashMap<String, Object>>();
		List<String> rightIdList  = operRightIdList(sysOperId);
		if(rightIdList!=null){
			Iterator<HashMap<String, Object>> iterator = allRights.iterator();
			while (iterator.hasNext()){
				HashMap<String, Object> next = iterator.next();
				String right = (String)next.get("id");
				if(right != null && rightIdList.contains(right)){
					opeRight.add(next);
				}
			}	
		}
		return opeRight;
	}
	
	//获取用户权限id组成的list
	private List<String> operRightIdList(String sysOperId){
		List<String> rightIdList = null;
		//获取用户权限
		List<SysOperRight> operRights = sysService.findOperRight(sysOperId);
		if(operRights !=null){
			rightIdList = new ArrayList<String>();
			for(SysOperRight operRight: operRights){
				rightIdList.add(operRight.getRight());
			}
		}
		return rightIdList;
	}
}
