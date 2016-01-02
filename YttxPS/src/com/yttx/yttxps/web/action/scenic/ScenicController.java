package com.yttx.yttxps.web.action.scenic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.vo.ScenicRequest;
import com.yttx.yttxps.service.IScenicService;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("scenic/")
public class ScenicController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IScenicService scenicService;
	
	/**
	 * 分页查询景区信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenic(ScenicRequest req)
    {  
		logger.debug("当前model {}",req.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyScenic(map);
		List<Scenic> list = scenicService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
}
