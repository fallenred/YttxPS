package com.yttx.comm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 时间工具类
 */
public class DateUtil{  
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat df24 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取两个日期中的每一天
	 * @param String startDate :格式 "yyyy-MM-dd"
	 * @param String endDate :格式 "yyyy-MM-dd"
	 * @return List<String> :返回格式 为"yyyy-MM-dd"字符串的集合
	 */
	public static List<String> getPerDayOfPeriodTime(String startDate,String endDate){
		List<String> days = new ArrayList<String>();
		
        try{
            Date dateOne = df.parse(startDate);
            Date dateTwo = df.parse(endDate);
            
            Calendar calendar =new GregorianCalendar();
            calendar.setTime(dateOne);
            while(!calendar.getTime().after(dateTwo)){   
                Date date = calendar.getTime();
                days.add(df.format(date));
                calendar.add(Calendar.DATE, 1);
            }
        }
        catch(Exception e){
            return days;
        }
		return days;
	}
	
	/**
	 * 将格式为"2016/05/03-2016/06/21"的字符串切成【2016/05/03，2016/05/03】
	 * 的数组，并获取下标为index的字符串
	 */
	public static String getDateStrFromDateRange(String range,int index){
		if(range==null){
			return null;
		}
		if(index<0||index>1){
			return null;
		}
		String[] dateStrs = range.split("-");
		if(dateStrs==null||dateStrs.length!=2){
			return null;
		}
		return dateStrs[index];
		
	}
	
	public static String getSysCurrentTime(){
		return df24.format(new Date());
	}
	
	public static String getFullTimeFormatStr(Date date){
		if(date==null){
			return "";
		}
		return df24.format(date);
		
	}
	
	public static String getDateFormatStr(Date date){
		if(date==null){
			return "";
		}
		return df.format(date);
	}
	
	public static void main(String[] args) {
		Date date =new Date();
		System.out.println(getFullTimeFormatStr(date));
	}
}  