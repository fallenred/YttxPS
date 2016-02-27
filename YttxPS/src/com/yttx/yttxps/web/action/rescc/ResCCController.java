package com.yttx.yttxps.web.action.rescc;

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

import com.yttx.yttxps.model.TResCCKey;
import com.yttx.yttxps.service.ITResCCService;
import com.yttx.yttxps.web.action.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("rescc/")
public class ResCCController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(ResCCController.class);
	
	@Autowired
	private ITResCCService resCCService;
	
	/**
	 * 资源类型-消费选项关系
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findResCC.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRouteArrange(TResCCKey resCCKey)
    {  
		logger.debug("当前查询条件 {}", resCCKey);		
		List<TResCCKey> list = resCCService.findTResCCKey(resCCKey.toMap());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
    }
}
