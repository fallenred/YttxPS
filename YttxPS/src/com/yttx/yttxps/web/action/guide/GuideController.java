package com.yttx.yttxps.web.action.guide;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TguideExample;
import com.yttx.yttxps.model.vo.GuideRequest;
import com.yttx.yttxps.service.IGuideService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("guide/")
public class GuideController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IGuideService guideService;

	/**
	 * 分页查询导游信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findGuide.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindGuide(GuideRequest req)
	{  
		logger.debug("当前查询条件 {}", req.getGuide());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyGuide(map);
		List<Tguide> list = guideService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 获取导游列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectGuide.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectGuide(GuideRequest req)
    {  
		TguideExample example = new TguideExample();
		req.copyTguide(example);
		List<Tguide> list = guideService.selectTguide(example);
		return list;
    }

	/**
	 * 新增导游信息
	 * @param Guide
	 * @return
	 */
	@RequestMapping(value="addGuide.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddGuide(Tguide guide)
	{  
		logger.debug("当前新增对象 {}", guide);
		try{
			guide.setStat(new BigDecimal(1));
			int ret = guideService.insert(guide);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 更新导游信息
	 * @param Guide
	 * @return
	 */
	@RequestMapping(value="editGuide.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditGuide(Tguide guide)
	{  
		logger.debug("当前更新对象 {}", guide);
		try{
			int ret = guideService.update(guide);
		}catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 删除导游信息
	 * @param Guide
	 * @return
	 */
	@RequestMapping(value="delGuide.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelGuide(@RequestParam(value = "no") String  no)
	{  
		logger.debug("当前删除key {}", no);
		try{
			int ret = guideService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
}
