package com.yttx.yttxps.web.action.restaurant;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.DateEditor;
import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.ResoucePrice;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.model.vo.RestaurantPriceReq;
import com.yttx.yttxps.model.vo.RestaurantWebRequest;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRestaurantService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 类描述：餐厅资源配置Controller
 * @author sunchao
 * @date 2016年2月16日 上午10:30:01
 */
@Controller
@Scope("prototype")
@RequestMapping("restaurant/")
public class RestaurantController extends BaseController {
	static Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private IPubService<?> pubService;
	
	@RequestMapping(value = "page.htm")
	public String openPage(){
		return "restaurant/restaurant";
	}
	
	/**
	 * 视图数据类型转换
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,  
            ServletRequestDataBinder binder) throws Exception 
	{
	    binder.registerCustomEditor(Date.class, new DateEditor()); //对于需要转换为Date类型的属性，使用DateEditor进行处理  
	}
	
	/**
	 * 分页查询餐厅信息信息
	 */
	@RequestMapping(value="list.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object listRestaurant(RestaurantWebRequest req)
	{  
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTRestaurant(map);
		List<TRestaurant> list = restaurantService.selectSelectivePage(map);
		if(list!=null){
			for(TRestaurant res:list){
				if(res.getRegionno()!=null)
					res.setRegionname(pubService.findRegionFullName(res.getRegionno()));
			}
		}
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 根据景区查询餐厅
	 */
	@RequestMapping(value="selectRestaurant.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object selectRestaurant(@RequestParam(value = "scenicNo[]") String[] scenicNo)
	{  
		Map<String, Object> map = new HashMap<String, Object>();
		if (scenicNo == null || scenicNo.length < 1){
			return null;
		}
		//map.put("scenicNo", Arrays.asList(scenicNo));
		return restaurantService.selectRestaurant(map);
    }
	
	/**
	 * 新增餐厅--打开新增页面
	 */
	@RequestMapping(value = "addPage.htm")
	public String openAddPage(Model model)
	{
		List<Scenic> scenics =pubService.findAllScennic();
		model.addAttribute("scenics", scenics);
		return "restaurant/add";
	}
	
	/**
	 * 新增餐厅--向后台提交数据
	 */
	@RequestMapping(value = "add.htm")
	@ResponseBody
	public Object addRestaurent(TRestaurant restaurant)
	{
		//验证数据
		Map<String,Object> validMap = validRestaurent(restaurant,"add");
		if((Boolean)validMap.get("succflag") == false){
			return JsonResult.jsonError((String)validMap.get("message"));
		}
		//向数据库中插入数据
		try{
			restaurantService.addRestaurent(restaurant);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 餐厅详情-->打开餐厅详情页面
	 */
	@RequestMapping(value = "show.htm")
	public String openShowPage(@RequestParam("no") String no,Model model)
	{
		//获取餐厅信息
		TRestaurant restaurant = restaurantService.selectRestaurantInfo(no);
		if(restaurant.getRegionno()!=null)
			restaurant.setRegionname(pubService.findRegionFullName(restaurant.getRegionno()));
		model.addAttribute("res", restaurant);
		return "restaurant/show";
	}
	
	/**
	 * 修改餐厅信息-->打开餐厅信息编辑界面
	 */
	@RequestMapping(value = "editPage.htm")
	public String openEditPage(@RequestParam("no") String no,Model model)
	{
		//获取餐厅信息
		TRestaurant restaurant = restaurantService.selectRestaurantInfo(no);
		if(restaurant.getRegionno()!=null)
			restaurant.setRegionname(pubService.findRegionFullName(restaurant.getRegionno()));
		model.addAttribute("res", restaurant);
		List<Scenic> scenics =pubService.findAllScennic();
		model.addAttribute("scenics", scenics);
		return "restaurant/edit";
	}
	
	/**
	 * 修改餐厅信息-->向后台提交数据
	 */
	@RequestMapping(value = "edit.htm")
	@ResponseBody
	public Object editRestaurent(TRestaurant restaurant)
	{
		//验证数据
		Map<String,Object> validMap = validRestaurent(restaurant,"edit");
		if((Boolean)validMap.get("succflag") == false){
			return JsonResult.jsonError((String)validMap.get("message"));
		}
		//向数据库中插入数据
		try{
			restaurantService.updateRestaurent(restaurant);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	/**
	 * 删除餐厅信息-->向后台提交数据
	 */
	@RequestMapping(value = "del.htm")
	@ResponseBody
	public Object deleteRestaurent(@RequestParam("no") String no)
	{
		//向数据库中插入数据
		try{
			restaurantService.deleteRestaurant(no);
		}catch(Exception e){
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	
	
	/**
	 * 餐厅资源定价-->打开餐厅资源价格展示页面
	 */
	@RequestMapping(value = "costPage.htm")
	public String openCostPage(@RequestParam("no") String no,Model model)
	{
		TRestaurant restaurant = restaurantService.selectRestaurantInfo(no);
		model.addAttribute("res",restaurant);
		return "restaurant/cost";
	}
	
	/**
	 * 餐厅资源定价-->ajax查询餐厅资源定价
	 */
	@RequestMapping(value = "costQuery.htm")
	@ResponseBody
	public Object queryCostPrice(@RequestParam("no") String no,
			@RequestParam("startdate") String startdate,
			@RequestParam("enddate") String endDate)
	{
		ResoucePrice resQue = new ResoucePrice();
		resQue.setResType("ct");//设置资源类型
		resQue.setResNo(no);//设置资源编号
		resQue.setStartDate(startdate);
		resQue.setEndDate(endDate);
		List<ResoucePrice> resoucePrices =null;
		try {
			resoucePrices=pubService.findResoucePrice(resQue);
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonData(resoucePrices);
	}
	
	/**
	 * 餐厅资源定价-->打开餐厅资源定价页面
	 */
	@RequestMapping(value = "showCPPage.htm")
	public String openCPPage(@RequestParam("no") String no,Model model)
	{
		TRestaurant restaurant = restaurantService.selectRestaurantInfo(no);
		model.addAttribute("res",restaurant);
		return "restaurant/price";
	}
	
	/**
	 * 餐厅资源定价-->打开餐厅资源定价页面
	 */
	@RequestMapping(value = "submitPrice.htm")
	@ResponseBody
	public Object submitRestaurantPrice(RestaurantPriceReq rpReq)
	{
		//验证数据是否正确
		Map<String,Object> validMap = RestaurantPriceReq(rpReq);
		if((Boolean)validMap.get("succflag") == false){
			return JsonResult.jsonError((String)validMap.get("message"));
		}
		try {
			restaurantService.submitPrice(rpReq);
		} catch (Exception e) {
			return JsonResult.jsonError(e.getMessage());
		}
		return JsonResult.jsonOk();
	}
	/*
	 * 验证提交的餐厅消费选项的价格是否提交完成
	 */
	private Map<String, Object> RestaurantPriceReq(RestaurantPriceReq rpReq) {
		Map<String, Object> flagMap = new HashMap<String,Object>();
		if(null==rpReq){
			flagMap.put("succflag", false);
			flagMap.put("message", "提交数据为空");
			return flagMap;
		}
		
		//非空 餐厅名称
		Boolean succFlag = true;
		String message = "";
		if(StringUtil.nullOrBlank(rpReq.getNo())){
			succFlag = succFlag && false;
			message += "餐厅名称为空<br/>";
		}
		
		if(rpReq.getStartDate()==null){
			succFlag = succFlag && false;
			message += "开始时间为空<br/>";
		}
		
		if(rpReq.getEndDate()==null){
			succFlag = succFlag && false;
			message += "结束时间为空<br/>";
		}
		
		if(rpReq.getEndDate().getTime()<rpReq.getStartDate().getTime()){
			succFlag = succFlag && false;
			message += "结束时间小于开始时间<br/>";
		}
		List<TCCPrice> prices = rpReq.getPrices();
		if(prices==null){
			succFlag = succFlag && false;
			message += "未录入任何消费选项<br/>";
		}
		for(TCCPrice price:prices){
			if(StringUtil.nullOrBlank(price.getFsCcno())){
				succFlag = succFlag && false;
				message += "消费选项名称未知<br/>";
			}
			if(price.getFdPrice()==null){
				succFlag = succFlag && false;
				message += "消费选项价格未知<br/>";
			}
		}
		flagMap.put("succflag", succFlag);
		flagMap.put("message", message);
		return flagMap;
	}

	/*
	 * 验证一个餐厅的数据是否完成和符合要求
	 */
	private Map<String, Object> validRestaurent(TRestaurant restaurant,String type)
	{
		Map<String, Object> flagMap = new HashMap<String,Object>();
		if(null==restaurant){
			flagMap.put("succflag", false);
			flagMap.put("message", "餐厅数据为空");
			return flagMap;
		}
		Boolean succFlag = true;
		String message = "";
		
		if("edit".equalsIgnoreCase(type)){//如果是修改餐厅信息，要验证餐厅编号
			if(StringUtil.nullOrBlank(restaurant.getNo())){
				succFlag = succFlag && false;
				message += "餐厅编号为空<br/>";
			}
		}
		
		if(StringUtil.nullOrBlank(restaurant.getScenicNo())){
			succFlag = succFlag && false;
			message += "所属景区为空<br/>";
		}
		//非空 餐厅名称
		if(StringUtil.nullOrBlank(restaurant.getName())){
			succFlag = succFlag && false;
			message += "餐厅名称为空<br/>";
		}
		//非空	所属地区
		if(StringUtil.nullOrBlank(restaurant.getRegionno())){
			succFlag = succFlag && false;
			message += "所属地区为空<br/>";
		}
		//非空	接待规模（单次接待桌数)
		if(null == restaurant.getScale()){
			succFlag = succFlag && false;
			message += "接待规模未输正整数<br/>";
		}
		//非空	菜品特色
		if(StringUtil.nullOrBlank(restaurant.getSpecial())){
			succFlag = succFlag && false;
			message += "特色菜品未选择<br/>";
		}
		//非空 餐厅等级
		if(StringUtil.nullOrBlank(restaurant.getLvl())){
			succFlag = succFlag && false;
			message += "餐厅等级未选择<br/>";
		}
		//非空  状态
		if(null == restaurant.getStat()){
			succFlag = succFlag && false;
			message += "状态未选择<br/>";
		}
		flagMap.put("succflag", succFlag);
		flagMap.put("message", message);
		return flagMap;
	}
}
