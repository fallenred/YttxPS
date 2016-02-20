package com.yttx.yttxps.web.action.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.model.TCCPriceKey;
import com.yttx.yttxps.service.ITccPriceService;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 资源消费选项定价
 * @author huangtao
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("tccPrice/")
public class TccPriceController extends BaseController {
static Logger logger = LoggerFactory.getLogger(TccPriceController.class);
	
	@Autowired
	private ITccPriceService tccPriceService;
	
	/**
	 * 查询资源价格
	 * @param req
	 * @return
	 */
	@RequestMapping(value="findTccPrice.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxfindTccPrice(TCCPriceKey key)
    {  
		logger.debug("当前查询条件 {}", key);
		return tccPriceService.selectTCCPrice(key);
    }
	
}
