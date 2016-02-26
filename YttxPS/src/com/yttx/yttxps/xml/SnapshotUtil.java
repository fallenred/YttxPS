package com.yttx.yttxps.xml;

import java.util.HashMap;
import java.util.List;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;

/**
 * 类描述：用于将特定格式字符串（快照）转化成相应的对象
 * @author sunchao
 * @date 2016年2月23日 下午3:32:27
 */
public class SnapshotUtil{
	
	public static List<Daylist> conver2DayList(String xml){
		if(StringUtil.nullOrBlank(xml)){
			return null;
		}
		Body body = ResScheduleXMLConverter.convert2Body(xml);
		return  body==null ? null : body.getDaylist();
	}
	
	public static List<Reslist> conver2ResList(String xml){
		if(StringUtil.nullOrBlank(xml)){
			return null;
		}
		Body body = ResScheduleXMLConverter.convert2Body(xml);
		return  body==null ? null : body.getReslist();
	}
	
	public static HashMap<String, String> converResListToDisplay(List<Reslist> list){
		if(list!=null){
			HashMap<String, String> resMap=new HashMap<String,String>();
			for(Reslist res:list){
				String resName=res.getResname();
				Cclist cclist=res.getCclist();
				StringBuilder resContent =new StringBuilder("消费选项名称："+cclist.getCcname()+"&nbsp; &nbsp; &nbsp;");
				resContent.append("价格："+cclist.getPrice()+"&nbsp; &nbsp; &nbsp;");
				if(cclist.getUsernum()!=null)
					resContent.append("数量："+cclist.getUsernum());
				resMap.put(resName, resContent.toString());
			}
			return resMap;
		}
		return null;
	}
}
