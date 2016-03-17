package com.yttx.yttxps.web.action.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictExample.Criteria;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.vo.OrderlistRequest;
import com.yttx.yttxps.service.IDictService;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Root;

@Controller
@Scope("prototype")
@RequestMapping("orderlist/")
public class OrderlistController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IOrderlistService orderlistService;
	@Autowired
	private IPubService<?> pubService;
	@Autowired
	private IDictService dictService;;
	
	/**
	 * 分页查询订单信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindOrderlist(OrderlistRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getOrderlistWithBLOBs());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyOrderlist(map);
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
		if ("1".equals(sessionEntity.getLevel()) || "2".equals(sessionEntity.getLevel())){
			map.remove("fsOperId");
		} else {
			//map.put("fsOperId", sessionEntity.getId());
		}
		List<TOrderlistWithBLOBs> list = orderlistService.selectSelectivePage(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(TOrderlistWithBLOBs orderlist : list){
				orderlist.setFsOperId(sessionEntity.getName());
				if(orderlist.getFsStartplace()!=null)
					orderlist.setRegionname(pubService.findRegionFullName(orderlist.getFsStartplace()));
			}
		}
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
		TOrderlistWithBLOBs orderlistWithBLOBs = orderlistService.selectByPrimaryKey(no);
		if (orderlistWithBLOBs == null)
			return null;
		Map<String, Body> map = new HashMap<String, Body>();
		//公共模糊快照
		if (StringUtils.isNotBlank(orderlistWithBLOBs.getFcCommfuzzysnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/" ,orderlistWithBLOBs.getFcCommfuzzysnapshot(), Root.class);
			map.put("commFuzzySnapshot", root.getBody());
		}
		//公共精确快照
		if (StringUtils.isNotBlank(orderlistWithBLOBs.getFcCommressnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderlistWithBLOBs.getFcCommressnapshot(), Root.class);
			map.put("commResSnapshot", root.getBody());
		} else if (StringUtils.isBlank(orderlistWithBLOBs.getFcCommressnapshot()) && "03".equals(orderlistWithBLOBs.getFsType())) {
			//定制线路-公共精确快照为空时创建空的body对象供页面做解析
			int days = orderlistWithBLOBs.getFiDays().toBigInteger().intValue();
			Body body = new Body();
			List<Daylist> list = new ArrayList<Daylist>();
			for (int i = 0; i < days; i++) {
				Daylist daylist = new Daylist();
				daylist.setDayflag(i+"");
				list.add(daylist);
			}
			body.setDaylist(list);
			map.put("commResSnapshot", body);
		}
		//定制线路-客户询价内容
		if ("03".equals(orderlistWithBLOBs.getFsType()) && StringUtils.isNotBlank(orderlistWithBLOBs.getFcSchedule())) {
			Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderlistWithBLOBs.getFcSchedule(), Root.class);
			DictExample dictExample = new DictExample();
			Criteria criteria = dictExample.createCriteria();
			criteria.andFsParentnoEqualTo("jtgj");
			Map<String, Object> dictMap = dictService.selectDictMap(dictExample);
			for (Daylist daylist : root.getBody().getDaylist()) {
				daylist.setTransport((String)dictMap.get(daylist.getTransport()));
			}
			map.put("fcSchedule", root.getBody());
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
			e.printStackTrace();
			logger.error("更新订单失败, 订单编号："+orderlist.getFsNo() + "\n" + e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新定制线路订单
	 * @param Orderlist
	 * @return
	 */
	@RequestMapping(value="editOrderlist4custom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditOrderlist4custom(TOrderlistWithBLOBs orderlist)
	{  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			HttpSession session = request.getSession();
			SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
			orderlist.setFsOperId(sessionEntity.getId());
			orderlistService.update4custom(orderlist);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("更新订单失败, 订单编号："+orderlist.getFsNo() + "\n" + e);
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
