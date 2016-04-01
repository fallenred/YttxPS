package com.yttx.yttxps.web.action.room;

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
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.model.RoomPrice;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.vo.RoomPriceRequest;
import com.yttx.yttxps.model.vo.RoomRequest;
import com.yttx.yttxps.service.IDictService;
import com.yttx.yttxps.service.IRoomPriceService;
import com.yttx.yttxps.service.IRoomService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月19日
 * @email me@lonvoy.com
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("room/")
public class RoomController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private IRoomService rootService;
	
	@Autowired
	private IRoomPriceService roomPriceService;
	
	@Autowired
	private IDictService dictService;

	/**
	 * 视图数据类型转换
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 分页查询房型信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findRoom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRoom(RoomRequest req) {
		logger.debug("当前查询条件 {}", req.getRoom());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyRoom(map);
		List<Room> list = rootService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}
	
	/**
     * 查询酒店房型（线路配置时使用）
     * add by huangtao
     * 2016-02-14
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "selectRoom.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxselectRoom(RoomRequest req) {
		logger.debug("当前查询条件 {}", req.getRoom());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyRoom(map);
		map.put("fiStat", BigDecimal.ONE);
		List<Room> list = rootService.selectSelective(map);
		return list;
	}

	/**
	 * 查询房型详情
	 * 
	 * @param index
	 * @return
	 */
	@RequestMapping(value = "findRoomInfo.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRoomInfo(@RequestParam(value = "id") String fsRoomno) {
		logger.debug("当前查询条件 {}", fsRoomno);
		Room info = rootService.selectRoomInfo(fsRoomno);
		return JsonResult.jsonData(info);
	}

	/**
	 * 新增房型信息
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addRoom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddRoom(Room root) {
		logger.debug("当前新增对象 {}", root);
		try {
			//注意:此处的唯一不准确，使用 oracle sequence 作为唯一
			if(root.getFsMeal() != null)
				root.setFsMeal(root.getFsMeal().replaceAll(",", ""));
			rootService.insert(root);
		} catch (Exception e) {
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 更新房型信息
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "editRoom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditRoom(Room root) {
		logger.debug("当前更新对象 {}", root);
		try {
			if(root.getFsMeal() != null)
				root.setFsMeal(root.getFsMeal().replaceAll(",", ""));
			rootService.update(root);
		} catch (Exception e) {
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 删除房型信息
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "delRoom.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelRoom(@RequestParam(value = "fsRoomno") String fsRoomno) {
		logger.debug("当前删除key {}", fsRoomno);
		try {
			rootService.delete(fsRoomno);
		} catch (Exception e) {
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 获取房型类型列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getRoomType.htm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> ajaxGetRoomType() {
		Map<String, Object> data = new HashMap<String, Object>();
		
		DictExample dictExample = new DictExample();
		com.yttx.yttxps.model.DictExample.Criteria dictCriteria = dictExample.createCriteria();
		dictCriteria.andFsParentnoEqualTo("fx");
		data = dictService.selectDictMap(dictExample);
		
		Map<String, Object> result = (Map<String, Object>)JsonResult.jsonOk();
		result.put("data", data);
		return result;
	}
	
	/**
	 * 分页查询房型信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findRoomPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRoomPrice(RoomPriceRequest req) {
		logger.debug("当前查询条件 {}", req.getRoomPrice());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyRoom(map);
		List<RoomPrice> list = roomPriceService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 新增房型信息
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addRoomPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddRoomPrice(TCCPrice price) {
		logger.debug("当前新增对象 {}", price);
		try {
			roomPriceService.insertRoomPrice(price);
		} catch (Exception e) {
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
	
	/**
	 * 删除房型价格信息
	 * 
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "delRoomPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelRoomPrice(TCCPrice price) {
		logger.debug("当前删除key {}", price);
		try {
			roomPriceService.delRoomPrice(price);
		} catch (Exception e) {
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
}
