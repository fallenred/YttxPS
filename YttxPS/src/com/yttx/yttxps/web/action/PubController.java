package com.yttx.yttxps.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yttx.comm.JqGridRequest;
import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Menu;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IScenicService;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("pub/")
public class PubController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IPubService pubService;

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
		Map map = new HashMap();
		map.put("succflag", 0);
		map.put("msg", null);
		map.put("data", list);
		return map;
    }
}
