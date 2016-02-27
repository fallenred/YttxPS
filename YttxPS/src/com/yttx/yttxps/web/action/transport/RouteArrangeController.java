package com.yttx.yttxps.web.action.transport;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeWithBLOBs;
import com.yttx.yttxps.model.TRouteCCKey;
import com.yttx.yttxps.model.vo.RouteArrangeRequest;
import com.yttx.yttxps.service.IRouteArrangeService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 线路配置
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("routeArrange/")
public class RouteArrangeController extends BaseController {
static Logger logger = LoggerFactory.getLogger(RouteArrangeController.class);
	
	@Autowired
	private IRouteArrangeService routeArrangeService;
	
	/**
	 * 分页查询线路信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findRouteArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRouteArrange(RouteArrangeRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getArrange());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTRouteArrange(map);
		List<TRouteArrange> list = routeArrangeService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectRouteArrange.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectRouteArrange(RouteArrangeRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getArrange());
		TRouteArrangeExample example = new TRouteArrangeExample();
		req.copyTRouteArrange(example);
		List<TRouteArrange> list = routeArrangeService.selectTRouteArrange(example);
		return list;
    }
	
	/**
	 * 新增线路信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="addRouteArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddRouteArrange(TRouteArrangeWithBLOBs routeArrange)
    {  
		logger.debug("当前新增对象 {}", routeArrange);
		try{
			routeArrangeService.insert(routeArrange);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 新增线路日程
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="addRouteCC.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddRouteCC(TRouteArrangeWithBLOBs routeArrange, String fiDays, String fsResno) {
		logger.debug("当前新增对象 {}", routeArrange);
		List<TRouteCCKey> list = routeArrange.getRoutecc();
		for(TRouteCCKey routecc : list) {
			routecc.setFiDayflag(new BigDecimal(fiDays));
			routecc.setFsResno(fsResno);
		}
		try{
//			routeArrangeService.insert(routeArrange);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新线路信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="editRouteArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditRouteArrange(TRouteArrangeWithBLOBs routeArrange)
    {  
		logger.debug("当前更新对象 {}", routeArrange);
		try{
			routeArrangeService.update(routeArrange);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除线路信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="delRouteArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelRouteArrange(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			routeArrangeService.delete(no);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
