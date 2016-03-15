package com.yttx.yttxps.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
		hashtable.put("ct","用餐标准");
		hashtable.put("cx","发团车型");
		hashtable.put("dy","随团导游");
		hashtable.put("bg","住宿标准");
		hashtable.put("gw","购物店");	
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
	
	/**
	 * 解析资源字符串-获取每天的资源列表
	 */
	public static List<Daylist> conver2DayList(String xml){
		Body body = objectFromXml(xml);
		return  body==null ? null : body.getDaylist();
	}
	
	/**
	 * 解析资源字符串-获取公共的资源列表
	 */
	public static List<Reslist> conver2ResList(String xml){
		Body body = objectFromXml(xml);
		return  body==null ? null : body.getReslist();
	}
	
	/**
	 * 将资源列表转化成一个map用于前端显示
	 */
	public static Map<String, List<Map<String, Object>>> converResListToDisplay(List<Reslist> list){
		if(list!=null){
			Map<String,List<Map<String, Object>>> resListMap=new HashMap<String,List<Map<String, Object>>>();
			for(Reslist res:list){
				String resType =res.getRestype();
				String resNo = res.getResno();
				if(StringUtil.nullOrBlank(resType)||"qj".equalsIgnoreCase(resType.trim())){
					continue;
				}
				String resTypeZH = hashtable.get(resType);
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("resNo", resNo);
				resMap.put("resName", res.getResname());
				List<Cclist> cclist=res.getCclist();
				if(cclist!=null){
					StringBuilder resContent = null;
					for(Cclist cc:cclist){
						resContent =new StringBuilder((StringUtil.nullOrBlank(cc.getCcname())?"":("消费选项:"+cc.getCcname())+","));
						resContent.append((cc.getPrice()==null?"":("价格:"+cc.getPrice())+","));
						resContent.append(cc.getUsernum()==null?"":("数量:"+cc.getUsernum()+","));
						int location = resContent.lastIndexOf(",");
						if(location>=0){
							resContent.setCharAt(resContent.lastIndexOf(","), ';');
						}
					}
					resMap.put("resContent", resContent);
				}
				List<Map<String, Object>> resDisList = resListMap.get(resTypeZH);
				if(resDisList==null){
					resDisList = new ArrayList<Map<String,Object>>();
				}
				resDisList.add(resMap);
				resListMap.put(resTypeZH, resDisList);
			}
			return resListMap;
		}
		return null;
	}
	
	/**
	 * 解析公用资源
	 */
	public static Map<String, List<Map<String, Object>>>  parseCommRes(String xml){
		List<Reslist> commResList=SnapshotUtil.conver2ResList(xml);
		Map<String, List<Map<String, Object>>> resMap= converResListToDisplay(commResList);
		return resMap;
	}
	
	/**
	 *解析每天的资源 
	 */
	public static List<Map<String, List<Map<String, Object>>>> parseDayRes(String xml){
		 List<Daylist> daylists = conver2DayList(xml);
		 if(daylists==null)
			 return null;
		Collections.sort(daylists,new Comparator<Daylist>(){
			@Override
			public int compare(Daylist o1, Daylist o2){
				return o1.getDayflag().compareTo(o2.getDayflag());
			}
		});
		
		List<Map<String, List<Map<String,Object>>>> list= new ArrayList<Map<String, List<Map<String,Object>>>>();
		Map<String, List<Map<String,Object>>> map =null;
		for(Daylist daylist:daylists){
			map = converResListToDisplay(daylist.getReslist());
			list.add(map);
		}
		return list;
	}
}
