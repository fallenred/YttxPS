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

import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.service.IPubService;

@Controller
@Scope("prototype")
@RequestMapping("pub/")
public class PubController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IPubService<?> pubService;

	/**
	 * 查询省市县
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findcity.htm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxfindcity(String key)
    {  
		logger.debug("当前查询的key {}",key);
		List<RegionMap> list = pubService.findRegionByManageNo(key);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("succflag", 0);
		map.put("msg", null);
		map.put("data", list);
		return map;
    }
}
