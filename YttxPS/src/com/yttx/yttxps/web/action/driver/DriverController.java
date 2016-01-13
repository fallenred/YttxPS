package com.yttx.yttxps.web.action.driver;

import java.math.BigDecimal;
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
import com.yttx.yttxps.model.Driver;
import com.yttx.yttxps.model.vo.DriverRequest;
import com.yttx.yttxps.service.IDriverService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月12日
 * @email me@lonvoy.com
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("driver/")
public class DriverController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IDriverService driverService;
	
	/**
	 * 视图数据类型转换
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,  
            ServletRequestDataBinder binder) throws Exception {
		//对于需要转换为Date类型的属性，使用DateEditor进行处理  
	    binder.registerCustomEditor(Date.class, new DateEditor()); 
	}
	
	/**
	 * 分页查询驾驶员信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findDriver.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenic(DriverRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getDriver());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyDriver(map);
		List<Driver> list = driverService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新增驾驶员信息
	 * @param driver
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addDriver.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddScenic(Driver driver)
    {  
		logger.debug("当前新增对象 {}", driver);
		try{
			//注意:此处的唯一不准确，不确定是否使用 oracle sequence 作为唯一
			driver.setIndex(new BigDecimal(System.currentTimeMillis()));
			driverService.insert(driver);
		}catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新驾驶员信息
	 * @param driver
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editDriver.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditScenic(Driver driver)
    {  
		logger.debug("当前更新对象 {}", driver);
		try{
			driverService.update(driver);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除驾驶员信息
	 * @param driver
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delDriver.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelScenic(@RequestParam(value = "id") BigDecimal  index)
    {  
		logger.debug("当前删除key {}", index);
		try{
			driverService.delete(index);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
