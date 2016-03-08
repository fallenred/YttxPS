package com.yttx.yttxps.xml;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;
import com.yttx.yttxps.xml.bean.Root;

/**
 * 类描述：用于将特定格式字符串（快照）转化成相应的对象
 * @author sunchao
 * @date 2016年2月23日 下午3:32:27
 */
public class SnapshotUtil{
	private static Map<String,String> hashtable = new Hashtable<String,String>();
	static{
		hashtable.put("mp","景区门票");
		hashtable.put("yl","娱乐项目");
		hashtable.put("ct","餐厅");
		hashtable.put("cx","车型");
		hashtable.put("dy","导游");
		hashtable.put("bg","宾馆");
		hashtable.put("gw","购物点");	
	}
	
	
	/**
	 * 将一个xml字符串转化成一个对象
	 */
	private static Body objectFromXml(String xml){
		if(StringUtil.nullOrBlank(xml)){
			return null;
		}
		String url=Constants.SNAPSHOT_NAMESPACE;
		Root root = ResScheduleXMLConverter.fromXml(url, xml, Root.class);
		return root==null?null:root.getBody();
	}
	
	public static List<Daylist> conver2DayList(String xml){
		Body body = objectFromXml(xml);
		return  body==null ? null : body.getDaylist();
	}
	
	public static List<Reslist> conver2ResList(String xml){
		Body body = objectFromXml(xml);
		return  body==null ? null : body.getReslist();
	}
	
	public static HashMap<String, String> converResListToDisplay(List<Reslist> list){
		if(list!=null){
			HashMap<String, String> resMap=new HashMap<String,String>();
			for(Reslist res:list){
				String resType =res.getRestype();
				if(StringUtil.nullOrBlank(resType)){
					continue;
				}
				String resName=hashtable.get(resType);
				
				List<Cclist> cclist=res.getCclist();
				StringBuilder resContent = null;
				if(!StringUtil.nullOrBlank(res.getResname())){
					resContent = new StringBuilder(res.getResname()+":");
				}
				
				if(cclist!=null){
					for(Cclist cc:cclist){
						resContent =new StringBuilder((cc.getCcname()==null?"":cc.getCcname())+",&nbsp;");
						resContent.append((cc.getPrice()==null?"":"价格--"+cc.getPrice())+", &nbsp;");
						resContent.append(cc.getUsernum()==null?"":"数量--"+cc.getUsernum()+";");
						resContent.append("<br/>");
					}
				}
				String content=resMap.get(resName);
				content=(content==null?resContent.toString():content+resContent.toString());
				resMap.put(resName, content);
			}
			return resMap;
		}
		return null;
	}
}
