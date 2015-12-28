package com.yttx.yttxps.comm;

import java.util.HashMap;
import java.util.Map;

import com.yttx.comm.StringUtil;



public class JsonResult {
	public static Object jsonError(String error)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "error");
		result.put("message", error);
		return result;
	}
	
	public static Object jsonError()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "error");
		return result;
	}

	public static Object jsonOk()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		return result;
	}

	public static Object jsonData(Object data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		result.put("data", data);
		return result;
	}
	
	public static Object jsonMap(HashMap<String, Object> data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		result.putAll(data);
		return result;
	}
	
	public static Object jsonByCheckMessage(String msg)
	{
		if (StringUtil.nullOrBlank(msg)) return JsonResult.jsonOk();
		return JsonResult.jsonError(msg);
	}

}
