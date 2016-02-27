package com.yttx.yttxps.xml;

import java.util.HashMap;
import java.util.List;

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
				String resName=res.getResname();
				List<Cclist> cclist=res.getCclist();
				StringBuilder resContent = null;
				if(cclist!=null){
					for(Cclist cc:cclist){
						resContent =new StringBuilder("消费选项名称："+cc.getCcname()+"&nbsp; &nbsp;");
						resContent.append("价格："+ cc.getPrice()+"&nbsp; &nbsp;");
						if(cc.getUsernum()!=null)
							resContent.append("数量："+cc.getUsernum());
						resContent.append("<br/>");
					}
				}
				resMap.put(resName, resContent.toString());
			}
			return resMap;
		}
		return null;
	}
}
