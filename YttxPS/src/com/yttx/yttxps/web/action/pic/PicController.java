package com.yttx.yttxps.web.action.pic;

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
import org.springframework.web.multipart.MultipartFile;

import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Pic;
import com.yttx.yttxps.model.vo.PicRequest;
import com.yttx.yttxps.service.IPicService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("pic/")
public class PicController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IPicService picService;
	
	 /**
	 * 分页查询图片信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindPic(PicRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getPic());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyPic(map);
		List<Pic> list = picService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新图片信息
	 * @param pic
	 * @return
	 */
	@RequestMapping(value="addPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddPic(Pic pic,
			@RequestParam(value = "file", required = true) MultipartFile file)
    {  
		logger.debug("当前新增对象 {}", pic);
		try{
			int ret = picService.insert(pic);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除图片信息
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="delPic.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelPic(@RequestParam(value = "index") BigDecimal  index)
    {  
		logger.debug("当前删除key {}", index);
		try{
			int ret = picService.delete(index);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
