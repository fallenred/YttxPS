package com.yttx.yttxps.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ISysService;

@Controller
@Scope("prototype")
@RequestMapping("pub/")
public class PubController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IPubService<?> pubService;
	
	@Autowired
	private ISysService sysService;

	/**
	 * 查询省市县
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findcity.htm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxfindCity(String key)
    {  
		logger.debug("当前查询的key {}",key);
		List<RegionMap> list = pubService.findRegionByManageNo(key);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succflag", 0);
		map.put("msg", null);
		map.put("data", list);
		return map;
    }
	
	/**
	 * 查询省市县名称
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findcityname.htm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxfindCityName(String no)
    {  
		logger.debug("当前查询的no {}",no);
		String name = pubService.findRegionFullName(no);
		Map<String, Object> map = (Map<String, Object>) JsonResult.jsonOk();
		map.put("name", name);
		return map;
    }
	
	/**
	 * 查询操作员
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findOperName.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxfindOperName(String operid){  
		logger.debug("当前查询的operid {}", operid);
		SysOper oper = this.sysService.findOperById(operid);
		return oper;
    }
}
