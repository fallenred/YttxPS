package com.yttx.yttxps.web.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;

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


import com.yttx.yttxps.model.Menu;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.service.ISysService;


/**
 * @author kereny
 *
 */

@Controller
@Scope("prototype")
public class LoginController extends BaseController{
	
	static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ISysService sysService;
	
		/**
	     *  主页面跳转处理
	     *  
		 * @author kereny
		 * @date 2015-6-5 下午4:30:22
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/")
	public String defaultpage()
    {  
		logger.debug("进入主页面");
		request.getSession().removeAttribute(Constants.SESSIONID);
		return "index";
    }
	
	@RequestMapping(value="index.htm")
	public String index()
    {  
		logger.debug("进入主页面");
		request.getSession().removeAttribute(Constants.SESSIONID);
		return "index";
    }
	
	
	@RequestMapping(value="home.htm")
	public String home()
    {  
		return "main";
    }

		/**
	     *  会员登录处理
		 * @author kereny
		 * @date 2015-6-4 下午1:17:18
		 * @param username
		 * @param password
		 * @param mac
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="login.htm",method=RequestMethod.POST)
	public String login(
			@RequestParam(value = "userid") String  userid,
            @RequestParam(value = "password") String  password, Model model)
     {  
		
		String hostIp = request.getRemoteAddr();
		logger.info("用户登录, IP:{} ID:{} 密码{} ", hostIp+ userid+ password);
		
		SysOper sysOper = sysService.findOperById(userid);
		if(sysOper != null)
		{
//			TODO:先屏蔽
//			if(!sysOper.getSysOperPwd().equalsIgnoreCase(password)){
//				model.addAttribute("message", "用户名或密码错误");
//				return "index";
//			}
			
			if(sysOper.getStat() != 1){
				model.addAttribute("message", "用户状态异常,请联系管理员");
				return "index";
			}
			
			SysDep dep = sysService.findDepByNo(sysOper.getDepNo());
			
			
			Map<String,Menu> sysUrlMap = (Map<String,Menu>) request.getSession().getServletContext().getAttribute(Constants.SYSMENULIST);
			List<String> rights = new ArrayList<String>();
			if(sysOper.getAdminType()!= 1){//如果是一般用户
				List<SysOperRight> list = sysService.findOperRight(sysOper.getSysOperId());
				for(SysOperRight r:list)
					rights.add(r.getRight());
				if(sysOper.getAdminType() == 2){//如果是部门管理员
					rights.add("0200");//平台用户管理相关权限
					rights.add("02a0");
					rights.add("02b0");
				}
			}		
			List<Menu> customTree = getCustomTree(request, rights, null,sysOper.getAdminType());
			Map<String,Menu> customAllowMap = new HashMap<String,Menu>();
			treeToMap(customTree, customAllowMap);
			Map<String,Menu> customRejectMap = new HashMap<String,Menu>();
			for (Entry<String, Menu> entry : sysUrlMap.entrySet()) {  
				if(!customAllowMap.containsKey(entry.getKey())){
					customRejectMap.put(entry.getKey(), entry.getValue());
				}
			}  
			
			SessionEntity  sessionEntity = new SessionEntity();
			sessionEntity.setCustomTree(customTree);
			sessionEntity.setCustomAllowMap(customAllowMap);
			sessionEntity.setCustomRejectMap(customRejectMap);
			
			if(dep != null){
				sessionEntity.setDepNo(dep.getDepNo());
				sessionEntity.setDepName(dep.getDepName());
			}
			sessionEntity.setName(sysOper.getSysOperName());
			sessionEntity.setLevel(sysOper.getAdminType());
			sessionEntity.setPwdStatus(sysOper.getPwdStat());
			sessionEntity.setId(sysOper.getSysOperId());

			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSIONID, sessionEntity);
			if(sysOper.getPwdStat() == 0){
				return "user/init";
			}else{
				return "main";
			}
		}else{
			model.addAttribute("message", "用户名或密码错误");
			return "index";
		}
    }
	
	
	@RequestMapping(value="comm/findTreeMenu.htm")
	@ResponseBody
	public Object ajaxfindTreeMenu(@RequestParam(value = "url") String  url,Model model)
    {  
		logger.debug("当前URL {}", url);
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		List<Menu> treeMenu = sessionEntity.getCustomTree();
		Map<String, Menu> allowMap = sessionEntity.getCustomAllowMap();

		
		Menu m = allowMap.get(url);
		if(m != null){
			setActionUrl(treeMenu, m.getId());
		}
		return JsonResult.jsonData(treeMenu);
    }
	
	/**
	 * 退出系统
	 */
	@RequestMapping(value="logout.htm")
	public String logout(){
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SESSIONID,null);
		return "index";
	}
	
	public boolean setActionUrl(List<Menu> trees, String key){
		boolean active = false;
		for (Menu m : trees) {
			List<Menu> subList = m.getSubMenu();
			if (subList != null) {
				m.setHasActive(setActionUrl(subList, key));				
			}else{
				if(m.getId().equalsIgnoreCase(key)){
					m.setHasActive(true);
					active = true;
				}else{
					m.setHasActive(false);
				}
			}
		}
		return active;
	}

	
	/**
	 * 
	 * 用户菜单树转成MAP的功能表
	 * @param list
	 * @param urlMap
	 */
	
	private void treeToMap(List<Menu> list, Map<String,Menu> urlMap){
		for (Menu row : list) {
			Menu menu = new Menu();
			menu.setId(row.getId());
			menu.setName(row.getName());
			menu.setUrl(row.getUrl());
			menu.setIcon(row.getIcon());
			menu.setHasActive(row.isHasActive());
			menu.setHasChild(row.isHasChild());
			List<Menu> subList = row.getSubMenu();
			if (subList != null) {
				treeToMap(subList, urlMap);				
			}else{
				urlMap.put(menu.getUrl(), menu);
			}
		}
	}

	/**
	 * 
	 * 取用户菜单树
	 * @param request
	 * @param rights
	 * @param rightTreeMenu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Menu> getCustomTree(HttpServletRequest request,
			List<String> rights, List<Menu> sysMenuList,Long adminType) {
		ServletContext servletContext = request.getSession().getServletContext();
		
		List<Menu> list = (List<Menu>) servletContext.getAttribute(Constants.SYSMENUTREE);	
		Map<String,Boolean> rightsMap = new HashMap<String,Boolean>();
		for (String right : rights)
			rightsMap.put(right, true);
		return getCustomTreeMenu(rightsMap, list, sysMenuList,adminType);
	}

	/**
	 * 
	 * 递归生成用户菜单树
	 * @param map
	 * @param list
	 * @param rightTreeMenu
	 * @return
	 */
	private List<Menu> getCustomTreeMenu(Map<String,Boolean> map, List<Menu> list, List<Menu> sysMenuList,Long adminType) {
		List<Menu> targetList = new ArrayList<Menu>();
		for (Menu row : list) {
			Menu targetMenu = new Menu();
			targetMenu.setId(row.getId());
			targetMenu.setName(row.getName());
			targetMenu.setUrl(row.getUrl());
			targetMenu.setIcon(row.getIcon());
			targetMenu.setHasActive(row.isHasActive());
			List<Menu> subList = row.getSubMenu();
			if (subList != null) {
				List<Menu> targetSubList = getCustomTreeMenu(map, subList, sysMenuList,adminType);
				if(targetSubList != null && targetSubList.size() > 0){
					targetMenu.setHasChild(true);
					targetMenu.setSubMenu(targetSubList);
					targetList.add(targetMenu);
				}
			}else{
				if(sysMenuList != null)
					sysMenuList.add(targetMenu);
				if ((!map.isEmpty() && map.containsKey(row.getId())) || adminType==1){
					targetList.add(targetMenu);
				}
			}
		}
		return targetList;
	}
}
