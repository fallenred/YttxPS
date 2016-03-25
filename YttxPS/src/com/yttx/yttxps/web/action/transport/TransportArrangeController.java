package com.yttx.yttxps.web.action.transport;

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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.comm.DateEditor;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.vo.TransportArrangeRequest;
import com.yttx.yttxps.service.ITransportArrangeService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 车型线路管理
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("transportArrange/")
public class TransportArrangeController extends BaseController {
static Logger logger = LoggerFactory.getLogger(TransportArrangeController.class);
	
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
	 * 分页查询车型线路信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findTransportArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTransportArrange(TransportArrangeRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getTransportArrange());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTransportArrange(map);
		List<TtransportArrange> list = transportArrangeService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取路线列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectTransportArrange.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectRouteArrange(TransportArrangeRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getTransportArrange());
		TtransportArrangeExample example = new TtransportArrangeExample();
		req.copyTransportArrange(example);
		List<TtransportArrange> list = transportArrangeService.selectTtransportArrangeView(example);
		return list;
    }
	
	/**
	 * 新增车型线路信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="addTransportArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTransportArrange(TtransportArrange ttransportArrange){  
		logger.debug("当前新增对象 {}", ttransportArrange);
		try{
			ttransportArrange.setFsNo("cx" + String.format("%08d", transportArrangeService.selectFsNo()));
			transportArrangeService.insert(ttransportArrange);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新车型线路信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="editTransportArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTransportArrange(TtransportArrange ttransportArrange)
    {  
		logger.debug("当前更新对象 {}", ttransportArrange);
		try{
			transportArrangeService.update(ttransportArrange);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除车型线路信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="delTransportArrange.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTransportArrange(@RequestParam(value = "no") String  no) {
		
		logger.debug("当前删除key {}", no);
		try{
			transportArrangeService.deleteByNo(no);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
