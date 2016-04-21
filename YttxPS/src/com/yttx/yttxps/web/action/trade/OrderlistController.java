package com.yttx.yttxps.web.action.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yttx.comm.FileUpload;
import com.yttx.comm.ObjectExcelView;
import com.yttx.comm.PathUtil;
import com.yttx.comm.road.RoadExcelRead;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.TOrderCusList;
import com.yttx.yttxps.model.TOrderCusListExample;
import com.yttx.yttxps.model.TOrderCusListExample.Criteria;
import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.model.TRemarksExample;
import com.yttx.yttxps.model.vo.OrderlistRequest;
import com.yttx.yttxps.service.IDictService;
import com.yttx.yttxps.service.IMsgService;
import com.yttx.yttxps.service.IOrderCusListService;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRemarksService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Root;

@Controller
@Scope("prototype")
@RequestMapping("orderlist/")
public class OrderlistController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(OrderlistController.class);
	
	@Autowired
	private IOrderCusListService orderCusListService;
	@Autowired
	private IOrderlistService orderlistService;
	@Autowired
	private IPubService<?> pubService;
	@Autowired
	private IRemarksService remarksService;
	@Autowired
	private IDictService dictService;
	@Autowired
	private IMsgService msgService;
	
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
		if (1l == sessionEntity.getLevel() || 2l == sessionEntity.getLevel()){
			map.remove("fsOperId");
		} else {
			map.put("fsOperId", sessionEntity.getId());
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
	 * 查询订单备注信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findRemarks.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRemarks(String orderId) {
		TRemarksExample example = new TRemarksExample();
		com.yttx.yttxps.model.TRemarksExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("fi_Seq");
		criteria.andFsOrderIdEqualTo(orderId);
		return remarksService.selectRemarks(example);
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
		Map<String, Object> map = new HashMap<String, Object>();
		//公共模糊快照
		if (StringUtils.isNotBlank(orderlistWithBLOBs.getFcCommfuzzysnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/" ,orderlistWithBLOBs.getFcCommfuzzysnapshot(), Root.class);
			map.put("commFuzzySnapshot", root.getBody());
		}
		//公共精确快照
		if (StringUtils.isNotBlank(orderlistWithBLOBs.getFcCommressnapshot())){
			Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderlistWithBLOBs.getFcCommressnapshot(), Root.class);
			map.put("commResSnapshot", root.getBody());
		} else if (StringUtils.isBlank(orderlistWithBLOBs.getFcCommressnapshot())) {
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
		if (StringUtils.isNotBlank(orderlistWithBLOBs.getFcSchedule())) {
			if ("03".equals(orderlistWithBLOBs.getFsType())) {
				Root root = ResScheduleXMLConverter.fromXml("http://www.cnacex.com/", orderlistWithBLOBs.getFcSchedule(), Root.class);
				map.put("fcSchedule", root.getBody());
			} else {
				map.put("fcSchedule", orderlistWithBLOBs.getFcSchedule());
			}
			/*DictExample dictExample = new DictExample();
			Criteria criteria = dictExample.createCriteria();
			criteria.andFsParentnoEqualTo("jtgj");
			Map<String, Object> dictMap = dictService.selectDictMap(dictExample);
			for (Daylist daylist : root.getBody().getDaylist()) {
				daylist.setTransport((String)dictMap.get(daylist.getTransport()));
			}*/
		}
		return map;
    }
	
	/**
	 * 新增订单信息
	 * @param Orderlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditOrderlist(TOrderlistWithBLOBs orderlist)
    {  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			HttpSession session = request.getSession();
			SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
			orderlist.setFsOperId(sessionEntity.getId());
			orderlistService.update(orderlist);
			this.msgService.saveMsg(orderlist, sessionEntity.getId());
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editOrderlist4custom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditOrderlist4custom(TOrderlistWithBLOBs orderlist) {  
		logger.debug("当前更新对象 {}", orderlist);
		try{
			HttpSession session = request.getSession();
			SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
			orderlist.setFsOperId(sessionEntity.getId());
			orderlistService.update4custom(orderlist);
			this.msgService.saveMsg(orderlist, sessionEntity.getId());
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("更新订单失败, 订单编号："+orderlist.getFsNo() + "\n" + e);
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 删除订单信息（将状态改为已删除状态）
	 * @param Orderlist
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@RequestMapping(value="delOrderlist.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelOrderlist(@RequestParam(value = "fsNo") String  fsNo)//修改参数名no to fsNo
    {  
		logger.debug("当前删除key {}", fsNo);
		try{
			int ret = orderlistService.delete(fsNo);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 导出游客名单
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportOrderCusList")
	public Object exportExcel(String orderId){
		ModelAndView mv = new ModelAndView();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("姓名");
			titles.add("电话");
			titles.add("证件类型");
			titles.add("证件号码");
			titles.add("备注");
			dataMap.put("titles", titles);

			//查询游客名单
			TOrderCusListExample example = new TOrderCusListExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsOrderIdEqualTo(new String(orderId.getBytes("ISO-8859-1"),"utf-8"));
			List<TOrderCusList> list = orderCusListService.selectByExample(example);
			List<Map<String, String>> varList = new ArrayList<Map<String, String>>();
			
			//查询证件类型
			DictExample example1 = new DictExample();
			com.yttx.yttxps.model.DictExample.Criteria criteria1 = example1.createCriteria();
			criteria1.andFsParentnoEqualTo("zjlx");
			Map<String, Object> zjlxMap = dictService.selectDictMap(example1);
			for(int i=0; i<list.size(); i++){
				Map<String, String> vpd = new HashMap<String, String>();
				vpd.put("var1", list.get(i).getFsName());	
				vpd.put("var2", list.get(i).getFsTel());
				vpd.put("var3", (String) zjlxMap.get(list.get(i).getFsIdtype()));
				vpd.put("var4", list.get(i).getFsId());
				vpd.put("var5", list.get(i).getFsMark());
				varList.add(vpd);
			}

			dataMap.put("varList", varList);
			//执行excel操作
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
			return (Map<String, Object>) JsonResult.jsonError("游客名单下载失败");
		}
		return mv;
	}
	
	/**
	 * 导入订单游客名单
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/importExcel.htm")
	public void importExcel(HttpServletResponse response, @RequestParam(value="excel",required=false) MultipartFile file, String orderId) throws Exception{
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Constants.FILEPATHFILE;
			
			String fileName =  FileUpload.fileUp(file, filePath, new Date().getTime()+"custExcel");							//执行上传
			List<Map<String, String>> list = (List)RoadExcelRead.readExcel(filePath, fileName, 1, 0, 0);	
			try {
				if (CollectionUtils.isNotEmpty(list)) {
					List<TOrderCusList> cusLists = new ArrayList<TOrderCusList>();
					//查询证件类型
					DictExample example = new DictExample();
					com.yttx.yttxps.model.DictExample.Criteria criteria = example.createCriteria();
					criteria.andFsParentnoEqualTo("zjlx");
					Map zjlxMap = dictService.selectDictMapName4key(example);
					/**
					 * modify by marongcai
					 * 上传名单的处理有问题，之前只是把第一条重复插入，并且没有判断null
					 * 2016-3-29
					 * modify by start
					 */
					for (int i = 0; i < list.size(); i++) {
						Map<String, String> map = list.get(i);
						TOrderCusList orderCusList = new TOrderCusList();
						orderCusList.setFsOrderId(orderId);
						orderCusList.setFiSeq(new BigDecimal(i));
						orderCusList.setFsName(map.get("var0") == null ? "" : map.get("var0").trim());
						orderCusList.setFsTel(map.get("var1") == null ? "" : map.get("var1").trim());
						orderCusList.setFsIdtype((String)zjlxMap.get(map.get("var2") == null ? "" : map.get("var2").trim()));
						orderCusList.setFsId(map.get("var3") == null ? "" : map.get("var3").trim());
						orderCusList.setFsMark(map.get("var4") == null ? "" : map.get("var4").trim());
						cusLists.add(orderCusList);
					}
					/**
					 * modify end
					 */
					orderCusListService.inser(cusLists);
				}
			} catch (Exception e) {
				String mgs = "导入游客名单失败";
				logger.error(mgs, e);
				response.getOutputStream().write(mgs.getBytes("utf-8"));
				return;
			}
		}
		response.getOutputStream().write("导入成功".getBytes("utf-8"));
	}
}
