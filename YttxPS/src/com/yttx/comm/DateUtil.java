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
	
	/**
	 * 获取两个日期中的每一天
	 * @param String startDate :格式 "yyyy-MM-dd"
	 * @param String endDate :格式 "yyyy-MM-dd"
	 * @return List<String> :返回格式 为"yyyy-MM-dd"字符串的集合
	 */
	public static List<String> getPerDayOfPeriodTime(String startDate,String endDate){
		List<String> days = new ArrayList<String>();
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
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
}  