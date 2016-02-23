package com.yttx.yttxps.web.action.trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.vo.OrderlistRequest;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;

@Controller
@Scope("prototype")
@RequestMapping("orderlist/")
public class OrderlistController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IOrderlistService orderlistService;
	
	/**
	 * 分页查询订单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindOrderlist(OrderlistRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getOrderlist());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyOrderlist(map);
		List<TOrderlistWithBLOBs> list = orderlistService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查询资源快照
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findCommSnapshot.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindCommSnapshot(String no)
    {  
		logger.debug("当前查询条件 {}", no);
		TOrderlistExample example = new TOrderlistExample();
		example.createCriteria().andFsNoEqualTo(no);
		List<TOrderlistWithBLOBs> list = orderlistService.selectTOrderlist(example);
		if (CollectionUtils.isEmpty(list))
			return null;
		Map<String, Body> map = new HashMap<String, Body>();
		//模糊快照
		if (StringUtils.isNotBlank(list.get(0).getFcCommfuzzysnapshot())){
			map.put("commFuzzySnapshot", ResScheduleXMLConverter.convert2Body(list.get(0).getFcCommfuzzysnapshot()));
		}
		//精确快照
		if (StringUtils.isNotBlank(list.get(0).getFcCommressnapshot())){
			map.put("commResSnapshot", ResScheduleXMLConverter.convert2Body(list.get(0).getFcCommressnapshot()));
		}
		return map;
    }
	
	/**
	 * 新增订单信息
	 * @param Orderlist
	 * @return
	 */
	@RequestMapping(value="addOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddOrderlist(TOrderlistWithBLOBs orderlist)
    {  
		logger.debug("当前新增对象 {}", orderlist);
		try{
			orderlistService.insert(orderlist);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新订单信息
	 * @param Orderlist
	 * @return
	 */
	@RequestMapping(value="editOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditOrderlist(TOrderlistWithBLOBs orderlist)
    {  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			orderlistService.update(orderlist);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除订单信息
	 * @param Orderlist
	 * @return
	 */
	@RequestMapping(value="delOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelOrderlist(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = orderlistService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
