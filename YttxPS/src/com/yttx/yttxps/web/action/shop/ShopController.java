package com.yttx.yttxps.web.action.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.yttx.yttxps.model.Tshop;
import com.yttx.yttxps.model.vo.ShopRequest;
import com.yttx.yttxps.service.IShopService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("shop/")
public class ShopController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IShopService shopService;

	/**
	 * 分页查询景区信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findShop.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindShop(ShopRequest req)
	{  
		logger.debug("当前查询条件 {}", req.getShop());
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		req.copyShop(map);
		List<Tshop> list = shopService.selectSelectivePage(map);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 获取购物店列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectShop.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectGuide(ShopRequest req, String scenicno)
    {  
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(scenicno)) {
			List<String> li = new ArrayList<String>();
			CollectionUtils.addAll(li, scenicno.split(","));
			map.put("scenicNo", li);
		}
		List<Tshop> list = shopService.selectTshopByMap(map);
		return list;
    }

	/**
	 * 新增景区信息
	 * @param Shop
	 * @return
	 */
	@RequestMapping(value="addShop.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxaddShop(Tshop shop)
	{  
		logger.debug("当前新增对象 {}", shop);
		try{
			shopService.insert(shop);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("新增失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 更新景区信息
	 * @param Shop
	 * @return
	 */
	@RequestMapping(value="editShop.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxeditShop(Tshop shop)
	{  
		logger.debug("当前更新对象 {}", shop);
		try{
			shopService.update(shop);
		}catch(Exception e){
			logger.error(e.getMessage());
			return (Map<String, Object>) JsonResult.jsonError("更新失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}

	/**
	 * 删除景区信息
	 * @param Shop
	 * @return
	 */
	@RequestMapping(value="delShop.htm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxdelShop(@RequestParam(value = "no") String  no)
	{  
		logger.debug("当前删除key {}", no);
		try{
			int ret = shopService.delete(no);
		}
		catch(Exception e){
			return (Map<String, Object>) JsonResult.jsonError("删除失败");
		}
		return (Map<String, Object>) JsonResult.jsonOk();
	}
}
