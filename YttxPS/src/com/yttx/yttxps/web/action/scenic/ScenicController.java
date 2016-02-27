package com.yttx.yttxps.web.action.scenic;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.vo.ScenicNoAndName;
import com.yttx.yttxps.model.vo.ScenicRequest;
import com.yttx.yttxps.service.IScenicService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("scenic/")
public class ScenicController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IScenicService scenicService;
	
	/**
	 * 分页查询景区信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenic(ScenicRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getScenic());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyScenic(map);
		List<Scenic> list = scenicService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查询所有景区编号和名称
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findAllScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindAllScenic(String no, String name, String regionno)
    {  
		logger.debug("查询所有景区编号和名称");
		ScenicRequest req = new ScenicRequest();
		req.setRows(Integer.MAX_VALUE);
		req.setPage(1);
		req.getScenic().setStat(new BigDecimal(1));
		req.getScenic().setNo(no);
		req.getScenic().setName(name);
		req.getScenic().setRegionno(regionno);
		
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyScenic(map);
		List<Scenic> list = scenicService.selectSelectivePage(map);
		List<ScenicNoAndName> retList = new ArrayList<ScenicNoAndName>();
		for (Scenic scenic : list) {
			ScenicNoAndName s = new ScenicNoAndName();
			s.setNo(scenic.getNo());
			s.setName(scenic.getName());
			retList.add(s);
		}
		return retList;
    }
	
	/**
	 * 新增景区信息
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="addScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddScenic(Scenic scenic)
    {  
		logger.debug("当前新增对象 {}", scenic);
		try{
		int ret = scenicService.insert(scenic);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新景区信息
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="editScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditScenic(Scenic scenic)
    {  
		logger.debug("当前更新对象 {}", scenic);
		try{
			int ret = scenicService.update(scenic);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除景区信息
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="delScenic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelScenic(@RequestParam(value = "no") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			int ret = scenicService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
