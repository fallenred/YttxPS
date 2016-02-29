package com.yttx.yttxps.web.action.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.Menu;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * @author sunchao
 * @date 2016年2月2日 下午6:16:29
 */
public class UserBasicController extends BaseController{
	
	@Autowired
	protected ISysService sysService;
	
	/**
	 * 获取超级管理员的权限
	 */
	protected List<HashMap<String, Object>> superManagerRights(){
		@SuppressWarnings("unchecked")
		List<Menu> orList = (List<Menu>) request.getSession()
                .getServletContext().getAttribute(Constants.SYSMENUTREE);
		List<Menu> allMenus =new ArrayList<>();
		for (Menu menu : orList) {
			if(!"0200".equalsIgnoreCase(menu.getId())){
				allMenus.add(menu);
			}
		}
		List<HashMap<String,Object>> menulist=new ArrayList<HashMap<String, Object>>();
		menuTreeToList(allMenus,menulist,null);
		return menulist;
	}
	
	/**
	 * 获取部门管理员的权限
	 */
	protected List<HashMap<String, Object>>  depManagerRights(Long depNo){
		List<HashMap<String, Object>> allMenus = superManagerRights();
		List<HashMap<String, Object>> deptMenus = new ArrayList<HashMap<String, Object>>();
		List<String> rightIdList  = depRightIdList(depNo);
		if(rightIdList!=null){
			Iterator<HashMap<String, Object>> iterator =allMenus.iterator();
			while (iterator.hasNext()){
				HashMap<String, Object> next = iterator.next();
				String right = (String)next.get("id");
				if(right != null && rightIdList.contains(right)){
					deptMenus.add(next);
				}
			}	
		}
		return deptMenus;
	}
	
	/**
	 * 根据stat获取指定状态的部门，如果stat为null，找出所有的部门
	 */
	protected List<SysDep> findDepsByStat(Long stat) {
		List<SysDep> deps=sysService.findDepsByStat(stat);
		if(deps == null ){
			deps = new ArrayList<SysDep>();
		}
		return deps;
	}
	
	/**
	 * 获取当前用户的管理身份
	 */
	protected Long currentAdminType(){
		String cOperId = sessionEntity.getId();
		SysOper cOper = sysService.findOperById(cOperId);
		return  cOper.getAdminType();
	}
	
	/**
	 * 获取一个部门的right，并将right的id组成一个list
	 */
	protected  List<String> depRightIdList(Long depNo){
		List<SysDepRight> rights = sysService.findDepRight(depNo);
		List<String> rightIdList = null;
		if(rights!=null&&rights.size()>0){
			rightIdList = new ArrayList<String>();
			for(SysDepRight depRight:rights){
				rightIdList.add(""+depRight.getRight());
			}
		}
		return rightIdList;
	}
	
	/**
	 * @paramList<HashMap<String, Object>> rightList ：权限
	 * @param List<String> rightIdList ：需要选中的权限id 清单
	 */
	protected void rightListChecked(List<HashMap<String, Object>> rightList,List<String> rightIdList){
		if(rightIdList !=null && rightIdList!=null){
			Iterator<HashMap<String, Object>> iterator =rightList.iterator();
			while(iterator.hasNext()){
				HashMap<String, Object> menumap = iterator.next();
				String menuId =(String) menumap.get("id");
				if(menuId !=null && rightIdList.contains(menuId)){
					menumap.put("checked", true);
				}
			}
		}
	}
	
	//将树形的菜单转化成一个扁平化的菜单,
	private void menuTreeToList(List<Menu> allMenu,List<HashMap<String, Object>> targetList,String pId){
		if(allMenu!=null){
			for(Menu menu : allMenu){
				HashMap<String,Object> map=new HashMap<String ,Object>();
				map.put("id",menu.getId());
				map.put("name", menu.getName());
				map.put("pId", pId);
				targetList.add(map);
				if(menu.isHasChild()&& null != menu.getSubMenu()){
					menuTreeToList(menu.getSubMenu(),targetList,menu.getId());
				}
			}
		}
	}		
}
