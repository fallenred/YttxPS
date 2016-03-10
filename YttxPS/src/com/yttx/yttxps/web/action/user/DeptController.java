package com.yttx.yttxps.web.action.user;

import java.util.HashMap;
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
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.vo.DeptAddRequest;
import com.yttx.yttxps.model.vo.SysDeptRequest;

/**
 * 类描述：用户机构管理控制器
 *@author sunchao
 *@date 2016年1月28日 下午2:52:02
 */
@Controller
@Scope("prototype")
@RequestMapping("user/")
public class DeptController extends UserBasicController{
	static Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	/**
	 * 打开用户机构管理页面
	 * @return String 
	 */
	@RequestMapping(value="depsetting.htm")
	public ModelAndView openDeptPage(){
		String sysOperId = sessionEntity.getId();//获取当前操作员名称
		SysOper oper = sysService.findOperById(sysOperId);
		Long adminType = oper.getAdminType();
		if(adminType==1){//如果是超级管理员
			return new ModelAndView("dept/dept");
		}else{
			Map<String,Object> model=fullDepInfo(sessionEntity.getDepNo());
			return new ModelAndView("dept/deptinfo",model);
		}	
	}
	
	/**
	 * 分页查询用户机构信息
	 * @param SysDeptRequest
	 * @return Object
	 */
	@RequestMapping(value="dept/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object listSysDept(SysDeptRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getSysDep());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copySysDep(map);
		List<SysDep> list = sysService.selectDepSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查看部门详细信息
	 */
	@RequestMapping(value="dept/show.htm")
	public ModelAndView showDept(@RequestParam(value="depNo")Long depNo)
    {  
		if(depNo == null){
			JsonResult.jsonError("缺少部门编号");
		}
		Map<String,Object> model=fullDepInfo(depNo);
		return new ModelAndView("dept/show",model);
    }
	
	
	/**
	 * 打开新增部门界面
	 */
	@RequestMapping(value="dept/addpage.htm")
	public String openDeptAddpage(Model model){
		List<HashMap<String,Object>> menulist = superManagerRights();
		model.addAttribute("menulist", menulist);
		return "dept/add";
	}
	
	
	/**
	 * 提交新增部门数据
	 */
	@RequestMapping(value="dept/addsubmit.htm",method = RequestMethod.POST)
	@ResponseBody
	public Object addDept(DeptAddRequest req){
		if(null==req.getSysDep()||StringUtil.nullOrBlank(req.getSysDep().getDepName())){//验证部门名称是否为空
			return JsonResult.jsonError("部门名称为空");
		}
		try{
			sysService.addSysDep(req);
			return JsonResult.jsonOk();
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
	}
	
	/**
	 * 打开修改部门界面
	 */
	@RequestMapping(value="dept/editpage.htm")
	public String openDeptEditpage(@RequestParam(value="depNo") long depNo,Model model){
		SysDep dep = sysService.findDepByNo(depNo);
		model.addAttribute("depInfo", dep);
		
		List<HashMap<String, Object>> menulist = superManagerRights();//所有的权限
		List<String> depRightIdList = depRightIdList(depNo);//部门权限id组成的list
		rightListChecked(menulist,depRightIdList);
		
		model.addAttribute("menulist", menulist);
		return "dept/edit";
	}
	
	/**
	 * 更新部门数据
	 */
	@RequestMapping(value="dept/editsubmit.htm",method=RequestMethod.POST)
	@ResponseBody
	public Object updateDept(DeptAddRequest req){
		SysDep dep =req.getSysDep();
		if(null == dep || null == dep.getDepNo()){//验证部门名称是否为空
			return JsonResult.jsonError("部门编号为空");
		}
		if(null == dep.getDepName()){//验证部门名称是否为空
			return JsonResult.jsonError("部门名称为空");
		}
		try{
			sysService.updateSysDep(req);
			return JsonResult.jsonOk();
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
	}
	
	/**
	 * 注销部门
	 */
	@RequestMapping(value="dept/cancel.htm",method = RequestMethod.POST)
	@ResponseBody
	public Object cancelDept(@RequestParam(value="depNo") Long depNo){
		if(depNo == null){
			JsonResult.jsonError("缺少部门编号");
		}
		try{
			sysService.cancelDeptByDepNo(depNo);
			return JsonResult.jsonOk();
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		
	}
	
	//将部门信息封装成一个map，包括部门信息和部门权限
	private Map<String,Object> fullDepInfo(Long depNo){
		Map<String, Object> model = new HashMap<String ,Object>();
		//添加部门信息
		SysDep sysDep = sysService.findDepByNo(depNo);
		model.put("depInfo", sysDep);
		
		//部门权限
		List<HashMap<String, Object>> menulist = depManagerRights(depNo);
		model.put("menulist", menulist);
		return model;
	}
	
	/**
	 *找到所有状态为正常的 的部门
	 */
	@RequestMapping(value="dept/findDepts.htm")
	@ResponseBody
	public  Map<String, Object>	findDepts(Long key){
		List<SysDep> list = sysService.findDepsByStat(key);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succflag", 0);
		map.put("msg", null);
		map.put("data", list);
		return map;
	}
}
