package com.yttx.yttxps.web.action.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yttx.yttxps.model.TOrderCustom;
import com.yttx.yttxps.model.TOrderCustomExample;
import com.yttx.yttxps.model.TOrderCustomExample.Criteria;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.model.TOrderlist;
import com.yttx.yttxps.model.vo.OrderCustomRequest;
import com.yttx.yttxps.service.IOrderCustomService;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Root;

@Controller
@Scope("prototype")
@RequestMapping("orderCustom/")
public class OrderCustomController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IOrderCustomService orderCustomService;
	@Autowired
	private IOrderlistService orderlistService;
	
	/**
	 * 分页查询批次订单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findOrderCustom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindOrderCustom(OrderCustomRequest req){  
		logger.debug("当前查询条件 {}", req.getOrderCustom());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyOrderCustom(map);
		List<TOrderCustomWithBLOBs> list = orderCustomService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查询批次订单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectOrderCustom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindOrderCustom(TOrderCustom orderCustom){  
		TOrderCustomExample example = new TOrderCustomExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsOrderIdEqualTo(orderCustom.getFsOrderId());
		example.setOrderByClause("fi_seq");
		List<TOrderCustomWithBLOBs> list = orderCustomService.selectTOrderCustom(example);
		//订单天数
		int days = 0;
		if (CollectionUtils.isNotEmpty(list)) {
			TOrderlist orderlist = orderlistService.selectByPrimaryKey(list.get(0).getFsOrderId());
			if (orderlist != null) {
				days = orderlist.getFiDays().intValue();
			}
		}
		for(TOrderCustomWithBLOBs customWithBLOBs : list){
			//转换精确快照文本
			if (StringUtils.isNotEmpty(customWithBLOBs.getFcRessnapshot())){
				Root root = ResScheduleXMLConverter.fromXml("http://www.yttx.com/", customWithBLOBs.getFcRessnapshot(), Root.class);
				customWithBLOBs.setBody(root.getBody());
			} else {
				//如果为空则根据订单天数生成一个空的body对象供页面解析展现
				Body body = new Body();
				body.setDaylist(new ArrayList<Daylist>());
				for (int i = 0; i < days; i++) {
					Daylist daylist = new Daylist();
					daylist.setDayflag(i+"");
					body.getDaylist().add(daylist);
				}
				customWithBLOBs.setBody(body);
			}
			//转换模糊快照
			if (StringUtils.isNotEmpty(customWithBLOBs.getFcFuzzysnapshot())) {
				Root root = ResScheduleXMLConverter.fromXml("http://www.yttx.com/", customWithBLOBs.getFcFuzzysnapshot(), Root.class);
				customWithBLOBs.setFuzzBody(root.getBody());
			}
		}
		return list;
    }
	
	/**
	 * 查询快照
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findSnapshot.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindSnapshot(BigDecimal no)
    {  
		logger.debug("当前查询条件 {}", no);
		TOrderCustomExample example = new TOrderCustomExample();
		example.createCriteria().andFiIdEqualTo(no);
		List<TOrderCustomWithBLOBs> list = orderCustomService.selectTOrderCustom(example);
		if (CollectionUtils.isEmpty(list))
			return null;
		Map<String, Body> map = new HashMap<String, Body>();
		//精确快照
		if (StringUtils.isNotEmpty(list.get(0).getFcRessnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.yttx.com/", list.get(0).getFcRessnapshot(), Root.class);
			map.put("resSnapshot", root.getBody());
		}
		//模糊快照
		if (StringUtils.isNotEmpty(list.get(0).getFcFuzzysnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.yttx.com/", list.get(0).getFcFuzzysnapshot(), Root.class);
			map.put("fuzzySnapshot", root.getBody());
		}
		return map;
    }
	
	/**
	 * 新增批次订单信息
	 * @param OrderCustom
	 * @return
	 */
	@RequestMapping(value="addOrderCustom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddOrderCustom(TOrderCustomWithBLOBs orderCustom)
    {  
		logger.debug("当前新增对象 {}", orderCustom);
		try{
			orderCustomService.insert(orderCustom);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新批次订单信息
	 * @param OrderCustom
	 * @return
	 */
	@RequestMapping(value="editOrderCustom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditOrderCustom(TOrderCustomWithBLOBs orderCustom)
    {  
		logger.debug("当前更新对象 {}", orderCustom);
		try{
			orderCustomService.update(orderCustom);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除批次订单信息
	 * @param OrderCustom
	 * @return
	 */
	@RequestMapping(value="delOrderCustom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelOrderCustom(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = orderCustomService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }

}
