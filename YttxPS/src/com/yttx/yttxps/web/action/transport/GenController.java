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
import com.yttx.yttxps.model.Tgen;
import com.yttx.yttxps.model.vo.GenRequest;
import com.yttx.yttxps.service.IGenService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

/**
 * 线路管理
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("gen/")
public class GenController extends BaseController {
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IGenService genService;
	
	/**
	 * 分页查询线路信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindGen(GenRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getGen());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyGen(map);
		List<Tgen> list = genService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新增线路信息
	 * @param Gen
	 * @return
	 */
	@RequestMapping(value="addGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddGen(Tgen gen)
    {  
		logger.debug("当前新增对象 {}", gen);
		try{
			gen.setFiIndex(new BigDecimal(genService.selectFiIndex()));
			int ret = genService.insert(gen);
		}
		catch(Exception e){
			e.printStackTrace();
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新线路信息
	 * @param Gen
	 * @return
	 */
	@RequestMapping(value="editGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditGen(Tgen gen)
    {  
		logger.debug("当前更新对象 {}", gen);
		try{
			int ret = genService.update(gen);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除线路信息
	 * @param Gen
	 * @return
	 */
	@RequestMapping(value="delGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelGen(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = genService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
