package com.yttx.yttxps.web.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.servlet.ModelAndView;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Menu;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.vo.DeptAddRequest;
import com.yttx.yttxps.model.vo.SysDeptRequest;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 类描述：用户机构管理控制器
 *@author sunchao
 *@date 2016年1月28日 下午2:52:02
 */
@Controller
@Scope("prototype")
@RequestMapping("user/")
public class DeptController extends BaseController{
	static Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private ISysService sysService;
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
		@SuppressWarnings("unchecked")
		//获取原始的menulist
		List<Menu> orList = (List<Menu>) request.getSession()
                .getServletContext().getAttribute(Constants.SYSMENUTREE);
		List<HashMap<String,Object>> menulist=new ArrayList<HashMap<String, Object>>();
		menuTreeToList(orList,null,menulist,null);
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
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 打开修改部门界面
	 */
	@RequestMapping(value="dept/editpage.htm")
	public String openDeptEditpage(@RequestParam(value="depNo") long depNo,Model model){
		SysDep dep = sysService.findDepByNo(depNo);
		model.addAttribute("depInfo", dep);
		@SuppressWarnings("unchecked")
		//获取原始的menulist
		List<Menu> orList = (List<Menu>) request.getSession()
                .getServletContext().getAttribute(Constants.SYSMENUTREE);
		
		List<SysDepRight> rights=sysService.findDepRight(depNo);
		List<String> rightIdList = getRightIdList(rights);
		
		List<HashMap<String,Object>> menulist=new ArrayList<HashMap<String, Object>>();
		menuTreeToList(orList,rightIdList,menulist,null);
		model.addAttribute("menulist", menulist);
		return "dept/edit";
	}
	
	/**
	 * 更新部门数据
	 */
	@RequestMapping(value="dept/editsubmit.htm",method=RequestMethod.POST)
	@ResponseBody
	public Object updateDept(DeptAddRequest req){
		logger.debug(req.getSysDep().getDepNo().toString());
		logger.debug(req.getRights().toString());
		
		SysDep dep =req.getSysDep();
		if(null == dep || null == dep.getDepNo()){//验证部门名称是否为空
			return JsonResult.jsonError("部门编号为空");
		}

		if(null == dep.getDepName()){//验证部门名称是否为空
			return JsonResult.jsonError("部门名称为空");
		}
		
		try{
			sysService.updateSysDep(req);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
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
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 将树形的菜单转化成一个扁平化的菜单,
	 */
	private List<HashMap<String, Object>> menuTreeToList(List<Menu> allMenu,List<String> rights,List<HashMap<String, Object>> targetList,String pId){
		//如果targetList为空，就创建一个list
		if(targetList==null){
			targetList=new ArrayList<HashMap<String, Object>>();
		}
		
		if(allMenu!=null){
			for(Menu menu : allMenu){
				HashMap<String,Object> map=new HashMap<String ,Object>();
				map.put("id",menu.getId());
				map.put("name", menu.getName());
				map.put("pId", pId);
				
				if(null!=rights&&rights.contains(menu.getId())){
					map.put("checked", true);
				}
				targetList.add(map);
				
				if(menu.isHasChild()&&null!=menu.getSubMenu()){
					menuTreeToList(menu.getSubMenu(),rights,targetList,menu.getId());
				}
			}
		}
		return targetList;
	}		
	
	/**
	 * 获取rights中的right的id，并组成一个list
	 */
	private List<String> getRightIdList(List<SysDepRight> rights){
		List<String> rightIdList = null;
		if(rights!=null&&rights.size()>0){
			rightIdList = new ArrayList<String>();
			for(SysDepRight depRight:rights){
				rightIdList.add(""+depRight.getRight());
			}
		}
		return rightIdList;
	}
	
	private Map<String,Object> fullDepInfo(Long depNo){
		Map<String, Object> model = new HashMap<String ,Object>();
		
		//添加部门信息
		SysDep sysDep = sysService.findDepByNo(depNo);
		model.put("depInfo", sysDep);
		
		@SuppressWarnings("unchecked")
		List<Menu> orList = (List<Menu>) request.getSession()
                .getServletContext().getAttribute(Constants.SYSMENUTREE);
		
		//获取部门权限
		List<SysDepRight> rights=sysService.findDepRight(depNo);
		List<String> rightIdList  = getRightIdList(rights);
		
		
		List<HashMap<String,Object>> menulist=new ArrayList<HashMap<String, Object>>();
		menuTreeToList(orList,rightIdList,menulist,null);
		
		Iterator<HashMap<String, Object>> iterator =menulist.iterator();
		while (iterator.hasNext()){
			HashMap<String, Object> next = iterator.next();
			if(next.get("checked")==null){
				iterator.remove();
			}
		}
		
		model.put("menulist", menulist);
		return model;
	}
}
