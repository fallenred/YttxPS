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
import com.yttx.yttxps.model.Ttransport;
import com.yttx.yttxps.model.TtransportExample;
import com.yttx.yttxps.model.vo.GenRequest;
import com.yttx.yttxps.model.vo.TransportRequest;
import com.yttx.yttxps.service.ITransportService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

/**
 * 车型管理
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("transport/")
public class TransportController extends BaseController {
static Logger logger = LoggerFactory.getLogger(TransportController.class);
	
	@Autowired
	private ITransportService transportService;
	
	/**
	 * 分页查询车型信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findTransport.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindTransport(TransportRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getTransport());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyTransport(map);
		List<Ttransport> list = transportService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取车型列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectTransport.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectTransport(GenRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getGen());
		TtransportExample example = new TtransportExample();
		example.createCriteria().andFiStatEqualTo(new BigDecimal(1));
		List<Ttransport> list = transportService.selectTtransport(example);
		return list;
    }
	
	/**
	 * 新增车型信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="addTransport.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddTransport(Ttransport transport)
    {  
		logger.debug("当前新增对象 {}", transport);
		try{
			transport.setFsNo(String.format("%010d", transportService.selectFsNo()));
			int ret = transportService.insert(transport);
		}
		catch(Exception e){
			e.printStackTrace();
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新车型信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="editTransport.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditTransport(Ttransport transport)
    {  
		logger.debug("当前更新对象 {}", transport);
		try{
			int ret = transportService.update(transport);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除车型信息
	 * @param Transport
	 * @return
	 */
	@RequestMapping(value="delTransport.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelTransport(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = transportService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
