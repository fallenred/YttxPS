package com.yttx.yttxps.listener;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yttx.comm.StringUtil;
import com.yttx.except.SystemException;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.Menu;


public class SysMenuListener implements ServletContextListener {
	
	static Logger logger = LoggerFactory.getLogger(SysMenuListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		initConfig(servletContextEvent.getServletContext());

	}
	
	/**
	 * 初始化系统配置
	 * 
	 * @param context
	 */
	private void initConfig(ServletContext servletContext) {
		
		URL url = null;
		SAXReader reader = new SAXReader();
		Document document;
		String sysmeun = servletContext.getInitParameter("sysmenu");
		try {
		
			logger.debug("菜单路径:{}", sysmeun);
			url = servletContext.getResource(sysmeun);
			document = reader.read(url.openStream());
			Element root = document.getRootElement();
			Element menu = root.element("menu");
			List<Menu> treeMenu = xmlToTree(menu);
			servletContext.setAttribute(Constants.SYSMENUTREE, treeMenu);
			
			Map<String,Menu> urlMap = new HashMap<String,Menu>();
			treeToMap(treeMenu, urlMap);
			
			servletContext.setAttribute(Constants.SYSMENULIST, urlMap);
			
		} catch (Exception e) {
			throw new SystemException(this, e.toString(), e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Menu> xmlToTree(Element target) {
		List<Element> items = (List<Element>) target.elements("item");
		List<Menu> list = new ArrayList<Menu>();
		for (Element item : items) {
			Menu mainMenu = new Menu();
			mainMenu.setId(item.attribute("id").getValue());
			mainMenu.setName(item.attribute("name").getValue());
			
			
			Element submenu = item.element("submenu");
			if (submenu != null){
				mainMenu.setSubMenu(xmlToTree(submenu));
				mainMenu.setHasChild(true);
				mainMenu.setIcon(item.attribute("icon").getValue());
			}else{
				Attribute urlAttr = item.attribute("url");
				if(urlAttr != null){
					String url = urlAttr.getValue();
					if(!StringUtil.nullOrBlank(url))
						mainMenu.setUrl(url);
				}
				mainMenu.setHasChild(false);
			}
			logger.debug("载入菜单 {}", mainMenu.toString());
			list.add(mainMenu);
		}
		return list;
	}
	
	
	public void treeToMap(List<Menu> list, Map<String,Menu> urlMap){
		for (Menu row : list) {
			Menu targetMenu = new Menu();
			targetMenu.setId(row.getId());
			targetMenu.setName(row.getName());
			targetMenu.setUrl(row.getUrl());
			List<Menu> subList = row.getSubMenu();
			if (subList != null) {
				treeToMap(subList, urlMap);				
			}else{
				urlMap.put(targetMenu.getUrl(), targetMenu);
			}
		}
	}
}
