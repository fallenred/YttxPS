package com.yttx.yttxps.web.action.transport;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.DateEditor;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.ResoucePrice;
import com.yttx.yttxps.model.RouteCCType;
import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeWithBLOBs;
import com.yttx.yttxps.model.TRouteCCExample;
import com.yttx.yttxps.model.TRouteCCExample.Criteria;
import com.yttx.yttxps.model.TRouteCCKey;
import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TtransportArrangeKey;
import com.yttx.yttxps.model.vo.RouteArrangeRequest;
import com.yttx.yttxps.service.IGuideService;
import com.yttx.yttxps.service.IRouteArrangeService;
import com.yttx.yttxps.service.ITransportArrangeService;
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
	
	@Autowired
	private IGuideService guideService;
	
	@Autowired
	private ITransportArrangeService transportArrangeService;
	
	/**
	 * 视图数据类型转换
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 线路资源定价-->打开线路资源价格展示页面
	 */
	@RequestMapping(value = "costPage.htm")
	public String openCostPage(@RequestParam("fsId") String fsId,Model model)
	{
		TRouteArrange routeArrange = new TRouteArrange();
		routeArrange.setFsId(fsId);
		model.addAttribute("res",routeArrange);
		return "routeArrange/cost";
	}
	
	/**
	 * 线路资源定价-->ajax查询线路资源定价
	 */
	@RequestMapping(value = "costQuery.htm")
	@ResponseBody
	public Object queryCostPrice(@RequestParam("fsId") String fsId,
			@RequestParam("startdate") String startdate,
			@RequestParam("enddate") String endDate)
	{
		List<Map<String, Object>> data = routeArrangeService.selectRouteArrangeInfo(fsId);
		return JsonResult.jsonData(data);
	}
	
	
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
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findUniqRouteArrange.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object findUniqRouteArrange(@RequestParam(value="fsId")String fsId)
    {  
		logger.debug("当前查询条件 {}", fsId);
		Map<String, Object> data = new HashMap<String, Object>();
		JsonResult.jsonData(data);
		TRouteArrangeWithBLOBs routeArrange = routeArrangeService.findTRouteArrange(fsId);
		data.put("routeArrange", routeArrange);
		if(routeArrange != null) {
			//导游信息
			TRouteCCExample routeCCExample = new TRouteCCExample();
			com.yttx.yttxps.model.TRouteCCExample.Criteria guideCriteria = routeCCExample.createCriteria();
			guideCriteria.andFsRoutenoEqualTo(routeArrange.getFsId());
			guideCriteria.andFsRestypeEqualTo("dy");
			guideCriteria.andFiDayflagEqualTo(BigDecimal.ZERO);
			List<TRouteCCKey> guides = routeArrangeService.findTRouteCCKey(routeCCExample);
			if(guides != null && guides.size() > 0) {
				Tguide guide = guideService.findTguide(guides.get(0).getFsResno());
				data.put("guide", guide);
			}
			//车型信息
			routeCCExample.clear();
			com.yttx.yttxps.model.TRouteCCExample.Criteria transportCriteria = routeCCExample.createCriteria();
			transportCriteria.andFsRoutenoEqualTo(routeArrange.getFsId());
			transportCriteria.andFiDayflagEqualTo(BigDecimal.ZERO);
			transportCriteria.andFsRestypeEqualTo("cx");
			List<TRouteCCKey> transports = routeArrangeService.findTRouteCCKey(routeCCExample);
			if(transports != null && transports.size() > 0) {
				TtransportArrangeKey transportArrange = transportArrangeService.findUniqTtransportArrange(transports.get(0).getFsResno());
				
				data.put("transportArrange", transportArrange);
			}
		}
		return JsonResult.jsonData(data);
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
	public Map<String, Object> ajaxaddRouteCC(TRouteArrangeWithBLOBs routeArrange) {
		logger.debug("当前新增对象 {}", routeArrange);
		try{
			routeArrangeService.insertRouteCC(routeArrange);
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
	
	/**
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findRouteCCType.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxfindRouteCCType(
			@Param(value = "fsRouteno")String fsRouteno, 
			@Param(value = "fiDayflag")BigDecimal fiDayflag,
			@Param(value = "fsRestype")String fsRestype) {
		
		logger.debug("当前查询条件 {}", fsRouteno, fiDayflag);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fsRouteno", fsRouteno);
		map.put("fiDayflag", fiDayflag);
		map.put("fsRestype", fsRestype);
		List<RouteCCType> list = routeArrangeService.findRouteCCType(map);
		return list;
    }
	
	/**
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findRouteCC.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRouteCC(TRouteCCKey routeCCKey) {
		
		logger.debug("当前查询条件 {}", routeCCKey);
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFiDayflagEqualTo(routeCCKey.getFiDayflag());
		criteria.andFsRoutenoEqualTo(routeCCKey.getFsRouteno());
		criteria.andFsRestypeEqualTo(routeCCKey.getFsRestype());
		criteria.andFsResnoEqualTo(routeCCKey.getFsResno());
		List<TRouteCCKey> list = routeArrangeService.findTRouteCCKey(example);
		return list;
    }
	
	/**
	 * 更新线路信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="editRouteCC.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditRouteCC(TRouteArrangeWithBLOBs routeArrange)
    {  
		logger.debug("当前更新对象 {}", routeArrange);
		try{
			routeArrangeService.updateRouteCC(routeArrange);
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
	@RequestMapping(value="delRouteCC.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelRouteCC(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			routeArrangeService.deleteRouteCC(no);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findRouteCCCount.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRouteCCCount(TRouteCCKey routeCCKey) {
		logger.debug("当前查询条件 {}", routeCCKey);
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFiDayflagEqualTo(routeCCKey.getFiDayflag());
		criteria.andFsRoutenoEqualTo(routeCCKey.getFsRouteno());
		criteria.andFsRestypeNotIn(Arrays.asList(new String[]{"cx","dy"}));
		int count = routeArrangeService.findRouteCCCount(example);
		return count;
    }
}
