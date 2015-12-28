package com.yttx.comm;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringUtil {

	private static final String GB_ENCODING = "gbk";
	private static final String ISO_ENCODING = "iso8859-1";

	public static String getChString(String str) {
		String chString = null;
		if (str == null) {
			return str;
		}
		try {
			byte[] bytes = str.getBytes(ISO_ENCODING);
			chString = new String(bytes, GB_ENCODING);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return chString;
	}

	public static String getISOString(String str) {
		String chString = null;
		if (str == null) {
			return str;
		}
		try {
			byte[] bytes = str.getBytes(GB_ENCODING);
			chString = new String(bytes, ISO_ENCODING);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return chString;
	}

	public static String getCurrentDate() {
		Calendar cal = new GregorianCalendar();
		Date trialTime = new Date();
		cal.setTime(trialTime);
		String currentDate = cal.get(Calendar.YEAR) + "-"
				+ (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DAY_OF_MONTH);
		return currentDate;
	}

	public static String getCurrentTime() {
		Calendar cal = new GregorianCalendar();
		Date trialTime = new Date();
		cal.setTime(trialTime);
		String currentTime = cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ (cal.get(Calendar.MINUTE) + 1) + ":"
				+ cal.get(Calendar.SECOND);
		return currentTime;
	}

	public static String getNow() {
		Calendar cal = new GregorianCalendar();
		Date trialTime = new Date();
		cal.setTime(trialTime);
		String curNow = new Integer(cal.get(Calendar.YEAR)).toString()
				+ new Integer(cal.get(Calendar.MONTH) + 1).toString()
				+ new Integer(cal.get(Calendar.DAY_OF_MONTH)).toString()
				+ new Integer(cal.get(Calendar.HOUR_OF_DAY)).toString()
				+ new Integer(cal.get(Calendar.MINUTE) + 1).toString()
				+ new Integer(cal.get(Calendar.SECOND)).toString();
		return curNow;

	}

	/**
	 * 加密处理
	 * 
	 * @author kereny
	 * @param string
	 * @param method
	 * @return
	 */
	public static String encrypt(String string, String method) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = string.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance(method);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断是否为空串
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:00:14
	 * @param param
	 * @return boolean
	 * 
	 */
	public static boolean nullOrBlank(String param) {
		return (param == null || param.length() == 0 || param.trim().equals("")) ? true
				: false;
	}

}
