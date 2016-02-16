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
import com.yttx.yttxps.model.TScenicGen;
import com.yttx.yttxps.model.TScenicGenExample;
import com.yttx.yttxps.model.vo.ScenicGenRequest;
import com.yttx.yttxps.service.IScenicGenService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 线路景区景区管理
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("scenicGen/")
public class ScenicGenController extends BaseController {
static Logger logger = LoggerFactory.getLogger(ScenicGenController.class);
	
	@Autowired
	private IScenicGenService scenicGenService;
	
	/**
	 * 分页查询线路景区信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findSelectGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenicGen(ScenicGenRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getScenicGen());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyScenicGen(map);
		List<TScenicGen> list = scenicGenService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 获取线路景区列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectScenicGen.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectScenicGen(ScenicGenRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getScenicGen());
		TScenicGenExample example = new TScenicGenExample();
		req.copyScenicGen(example);
		List<TScenicGen> list = scenicGenService.selectScenicGen(example);
		return list;
    }
	
	/**
	 * 新增线路景区信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="addSelectGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddScenicGen(TScenicGen scenicGen)
    {  
		logger.debug("当前新增对象 {}", scenicGen);
		try{
			//scenicGen.setFiIndex(new BigDecimal(scenicGenService.selectFiIndex()));
			scenicGenService.insert(scenicGen);
		}
		catch(Exception e){
			e.printStackTrace();
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新线路景区信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="editSelectGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditScenicGen(TScenicGen scenicGen)
    {  
		logger.debug("当前更新对象 {}", scenicGen);
		try{
			scenicGenService.update(scenicGen);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除线路景区信息
	 * @param Gen
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="delSelectGen.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelScenicGen(@RequestParam(value = "no") BigDecimal  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			scenicGenService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
