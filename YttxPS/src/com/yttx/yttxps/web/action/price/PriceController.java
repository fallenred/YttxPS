package com.yttx.yttxps.web.action.price;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.vo.PriceRequest;
import com.yttx.yttxps.service.ITccPriceService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 资源价格
 * @author
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("price/")
public class PriceController extends BaseController {

	static Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	private ITccPriceService priceService;
	
	/**
	 * 查询资源价格
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findResPrice.htm", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxfindRouteArrange(PriceRequest req)
    {  
		logger.debug("当前查询条件 {}", req);
		Map<String, Object> map = new HashMap<String, Object>();
		req.copyPage(map);
		List<TCCPrice> list = priceService.selectTCCPrice(map);
		map.put("rows", list);
		return map;
    }
}
