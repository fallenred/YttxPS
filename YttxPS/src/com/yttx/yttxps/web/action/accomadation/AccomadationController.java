package com.yttx.yttxps.web.action.accomadation;

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
import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.JsonResult;
import com.yttx.yttxps.model.Accomadation;
import com.yttx.yttxps.model.vo.AccomadationRequest;
import com.yttx.yttxps.service.IAccomadationService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月18日
 * @email me@lonvoy.com
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("accomadation/")
public class AccomadationController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(AccomadationController.class);
	
	@Autowired
	private IAccomadationService accomadationService;
	
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
	 * 分页查询宾馆信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findAccomadation.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindAccomadation(AccomadationRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getAccomadation());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyAccomadation(map);
		List<Accomadation> list = accomadationService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 查询宾馆列表（线路配置时使用）
	 * add by huangtao
	 * 2016-02-14
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectAccomadation.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxselectAccomadation(AccomadationRequest req)
    {
		logger.debug("当前查询条件 {}", req.getAccomadation());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyAccomadation(map);
		List<Accomadation> list = accomadationService.selectSelective(map);
		return list;
    }
	
	/**
	 * 查询宾馆详情
	 * @param index
	 * @return
	 */
	@RequestMapping(value="findAccomadationInfo.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindAccomadationInfo(@RequestParam(value = "id") String no)
    {  
		logger.debug("当前查询条件 {}", no);
		Accomadation info = accomadationService.selectAccomadationInfo(no);
		return JsonResult.jsonData(info);
    }
	
	/**
	 * 新增宾馆信息
	 * @param accomadation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addAccomadation.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddScenic(Accomadation accomadation)
    {
		logger.debug("当前新增对象 {}", accomadation);
		try{
			if(StringUtil.nullOrBlank(accomadation.getNo()))
				return (Map<String, Object>) JsonResult.jsonError("酒店代码未输入！");
			
			Accomadation info = accomadationService.selectAccomadationInfo(accomadation.getNo());
			if(info != null && StringUtil.nullOrBlank(info.getNo())){
				return (Map<String, Object>) JsonResult.jsonError("该酒店信息已存在，您可通过酒店代码【"+info.getNo()+"】查询");
			}
			accomadationService.insert(accomadation);
		}catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新宾馆信息
	 * @param accomadation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editAccomadation.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditScenic(Accomadation accomadation)
    {  
		logger.debug("当前更新对象 {}", accomadation);
		try{
			accomadationService.update(accomadation);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除宾馆信息
	 * @param accomadation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delAccomadation.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelScenic(@RequestParam(value = "id") String no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			accomadationService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
