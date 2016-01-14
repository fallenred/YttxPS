package com.yttx.yttxps.web.action.car;

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
import com.yttx.yttxps.model.Car;
import com.yttx.yttxps.model.vo.CarRequest;
import com.yttx.yttxps.service.ICarService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("car/")
public class CarController extends BaseController {
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ICarService carService;
	
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
	 * 分页查询车辆信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findCar.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindScenic(CarRequest req)
    {  
		logger.debug("当前查询条件 {}", req.getCar());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyCar(map);
		List<Car> list = carService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
    }
	
	/**
	 * 新增车辆信息
	 * @param car
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addCar.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddScenic(Car car)
    {  
		logger.debug("当前新增对象 {}", car);
		try{
			carService.insert(car);
		}catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 更新车辆信息
	 * @param car
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editCar.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditScenic(Car car)
    {  
		logger.debug("当前更新对象 {}", car);
		try{
			carService.update(car);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
	
	/**
	 * 删除车辆信息
	 * @param car
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delCar.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelScenic(@RequestParam(value = "id") String  no)
    {  
		logger.debug("当前删除key {}", no);
		try{
			carService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
    }
}
