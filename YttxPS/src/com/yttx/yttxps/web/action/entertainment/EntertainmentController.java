package com.yttx.yttxps.web.action.entertainment;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.DateEditor;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TEntertainment;
import com.yttx.yttxps.model.TEntertainmentExample;
import com.yttx.yttxps.model.vo.EntertainmentRequest;
import com.yttx.yttxps.model.vo.GenRequest;
import com.yttx.yttxps.service.IEntertainmentService;
import com.yttx.yttxps.web.action.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("entertainment/")
public class EntertainmentController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(EntertainmentController.class);
	
	@Autowired
	private IEntertainmentService entertainmentService;
	
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
	 * 分页查询娱乐项目信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findEntertainment.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindEntertainment(EntertainmentRequest req) {  
		logger.debug("当前查询条件 {}", req.getEntertainment());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyEntertainment(map);
		List<TEntertainment> list = entertainmentService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 根据景区查询娱乐项目
	 * add by huangtao
	 * 2016-02-24
	 */
	@RequestMapping(value="selectEntertainment.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object selectRestaurant(@RequestParam(value="scenicNo[]", required=false)String[] scenicNo, String stat)
	{  
		Map<String, Object> map = new HashMap<String, Object>();
		if (scenicNo != null && scenicNo.length > 0){
			map.put("scenicNo", Arrays.asList(scenicNo));
		}
		if (StringUtils.isNoneBlank(stat)) map.put("fiStat", stat);
		return entertainmentService.selectEntertainment(map);
    }
	
	/**
	 * 新增娱乐项目信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="addEntertainment.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddEntertainment(TEntertainment entertainment) {  
		logger.debug("当前新增对象 {}", entertainment);
		try{
			entertainmentService.insert(entertainment);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 更新娱乐项目信息
	 * @param TEntertainment
	 * @return
	 */
	@RequestMapping(value="editEntertainment.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditEntertainment(TEntertainment entertainment) {  
		logger.debug("当前更新对象 {}", entertainment);
		try{
			entertainmentService.update(entertainment);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 删除娱乐项目信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="delEntertainment.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelEntertainment(@RequestParam(value = "no") String  no, Date ftStartdate, Date ftEnddate) {  
		logger.debug("当前删除key {}", no);
		try{
			entertainmentService.delete(no);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 分页查询娱乐项目价格信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findEntertainmentPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxEntertainmentPrice(EntertainmentRequest req) {  
		logger.debug("当前查询条件 {}", req.getEntertainment());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyEntertainment(map);
		List<TEntertainment> list = entertainmentService.selectEntertainmentPricePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取娱乐项目列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectEntertainment.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectGen(GenRequest req) {  
		logger.debug("当前查询条件 {}", req.getGen());
		TEntertainmentExample example = new TEntertainmentExample();
		List<TEntertainment> list = entertainmentService.selectEntertainment(example);
		return list;
    }
	
	/**
	 * 获取娱乐项目列表
	 * @param scenicNo
	 * @return
	 */
	@RequestMapping(value="selectEntertainmentDynamic.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object selectEntertainmentByScenic(@RequestParam(value = "scenicNo") String[] scenicNo) {  
		logger.debug("当前查询条件 {}", scenicNo.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scenicNo", Arrays.asList(scenicNo));
		map.put("fiStat", BigDecimal.ONE);
		List<TEntertainment> list = entertainmentService.selectEntertainmentDynamic(map);
		return list;
    }
	
	/**
	 * 新增娱乐项目价格信息
	 */
	@RequestMapping(value="addEntertainmentPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxAddEntertainmentPrice(TEntertainment entertainment) {  
		logger.debug("当前新增对象 {}", entertainment);
		try{
			entertainmentService.insertEntertainmentPrice(entertainment);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除娱乐项目价格信息
	 */
	@RequestMapping(value="delEntertainmentPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTicketPrice(TCCPrice tccPrice) {  
		logger.debug("当前删除key {}", tccPrice);
		try{
			entertainmentService.deleteEntertainmentPrice(tccPrice);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新娱乐项目价格信息
	 * @param Ticket
	 * @return
	 */
	@RequestMapping(value="editEntertainmentPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxEditEntertainmentPrice(TEntertainment entertainment) {  
		logger.debug("当前更新对象 {}", entertainment);
		try{
			entertainmentService.updateEntertainmentPrice(entertainment);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
