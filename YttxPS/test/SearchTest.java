import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yttx.comm.StringUtil;
import com.yttx.except.SystemException;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.Menu;




public class SearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*SearchTest st = new SearchTest();
		st.xml();*/
		BigDecimal bigDecimal = new BigDecimal("-1.00");
		System.out.println(bigDecimal.toString());
		

	}
	
	
	
	public void xml(){
		URL url = null;
		SAXReader reader = new SAXReader();
		Document document;
		try {
			 //将订单信息写入文件
            File inputXML=new File("D:\\JavaDev\\yttx\\YttxPS\\WebRoot\\WEB-INF\\menu.xml");       
			document = reader.read(inputXML);
			Element root = document.getRootElement();
			Element menu = root.element("menu");
			List<Menu> list = xmlToTree(menu);
			
			for(Menu m:list){
				System.out.println(m.toString());
			}
			
			Map<String,Menu> urlMap = new HashMap<String,Menu>();
			treeToMap(list, urlMap);
			
			for (Entry<String, Menu> entry : urlMap.entrySet()) {  
				  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			  
			}  
			
			
			List<String> rights = new ArrayList<String>();
			String right = "03d0";
			rights.add(right);
			String right1 = "02b0";
			
			
			rights.add(right1);
			
			String right2= "03a3";
			rights.add(right2);
			
			
			List<Menu> menuAllList = new ArrayList<Menu>();
			
			Map<String,Boolean> rightsMap = new HashMap<String,Boolean>();
			for (String r : rights)
				rightsMap.put(r, true);
				
			List<Menu> rsList  = setExistMenu(rightsMap, list, menuAllList);
					System.out.println("----------------------------------");
			for(Menu m:rsList){
					System.out.println(m.toString());
			}
			
			System.out.println("----------------------------------");
			for(Menu m:menuAllList){
					System.out.println(m.toString());
			}
			
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
				System.out.print("--");
				mainMenu.setSubMenu(xmlToTree(submenu));
				mainMenu.setHasChild(true);
			}
			else{
				Attribute urlAttr = item.attribute("url");
				if(urlAttr != null){
					String url = urlAttr.getValue();
					if(!StringUtil.nullOrBlank(url))
						mainMenu.setUrl(url);
				}
				mainMenu.setHasChild(false);
				System.out.println("\t"+mainMenu.getName()+mainMenu.getUrl());
			}
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
	
	@SuppressWarnings("unchecked")
	private List<Menu> parseMenu(Element target) {
		List<Element> items = (List<Element>) target.elements("item");
		List<Menu> list = new ArrayList<Menu>();
		for (Element item : items) {
			Menu mainMenu = new Menu();
			mainMenu.setId(item.attribute("id").getValue());
			mainMenu.setName(item.attribute("name").getValue());
			
/*			Attribute nockeck = item.attribute("nockeck");
			if(nockeck!= null)
				mainMenu.setNockeck("1".equals(nockeck.getValue())?true:false);*/
			Element submenu = item.element("submenu");
			if (submenu != null){
				mainMenu.setSubMenu(parseMenu(submenu));
				mainMenu.setHasChild(true);
			}
			else{
				Attribute urlAttr = item.attribute("url");
				if(urlAttr != null){
					String url = urlAttr.getValue();
					if(!StringUtil.nullOrBlank(url))
						mainMenu.setUrl(url);
				}
				mainMenu.setHasChild(false);
			}
			list.add(mainMenu);
		}
		return list;
	}
	
	
	
	/**
	 * 取得用户菜单
	 * 
	 * @param power
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Menu> getMenuList(HttpServletRequest request,
			List<String> rights, List<Menu> menuAllList) {
		ServletContext servletContext = request.getSession().getServletContext();
		
		List<Menu> list = (List<Menu>) servletContext.getAttribute(Constants.SYSMENUTREE);	
		Map<String,Boolean> rightsMap = new HashMap<String,Boolean>();
		for (String right : rights)
			rightsMap.put(right, true);
		return setExistMenu(rightsMap, list, menuAllList);
	}
	
	/**
	 * 选中存在的菜单
	 * 
	 * @param rigthType
	 * @param list
	 * @param targetList
	 */
	
	
	private List<Menu> setExistMenu(Map<String,Boolean> map, List<Menu> list, List<Menu> menuAllList) {
		List<Menu> targetList = new ArrayList<Menu>();
		for (Menu row : list) {
			Menu targetMenu = new Menu();
			targetMenu.setId(row.getId());
			targetMenu.setName(row.getName());
			targetMenu.setUrl(row.getUrl());
			
			//if (map.containsKey(row.getId())||row.isNockeck())

			
			List<Menu> subList = row.getSubMenu();
			if (subList != null) {
				List<Menu> targetSubList = setExistMenu(map, subList, menuAllList);
				

				/*
				boolean childFlg = false;
				for(Menu item : targetSubList){
					if(item.isExistFlg())
						childFlg = true;
					item.setParentId(row.getId());
				}
				*/
				
				
				if(targetSubList != null && targetSubList.size() > 0){
					targetMenu.setHasChild(true);
					targetMenu.setSubMenu(targetSubList);
					targetList.add(targetMenu);
				}
				
				
			}else{
				menuAllList.add(targetMenu);
				
				if (map.containsKey(row.getId())){
					targetList.add(targetMenu);
				}
			}

			
			
			/*if(targetMenu.getId().equals("0200") || targetMenu.getId().equals("02a0") || targetMenu.getId().equals("02b0")){
				if(!"0".equals(loginData.getAdmin()))
					targetList.add(targetMenu);
			}else {
				targetList.add(targetMenu);
			}*/
			/*
			if(!row.isNockeck())
				menuAllList.add(targetMenu);
			*/
		}
		return targetList;
	}
	
	

}
