package com.yttx.yttxps.web.action.transport;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceExample.Criteria;
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
	@RequestMapping(value="findTccPriceByKey.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxfindTccPriceByKey(TCCPriceKey key)
    {  
		logger.debug("当前查询条件 {}", key);
		return tccPriceService.selectTCCPriceByKey(key);
    }
	
	/**
	 * 
	 * @param tccPrice
	 * @param date 购票日期
	 * @return
	 */
	@RequestMapping(value="findTccPrice.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxfindTccPrice(TCCPrice tccPrice)
    {  
		logger.debug("当前查询条件 {}", tccPrice);
		
		TCCPriceExample example = new TCCPriceExample();
		Criteria criteria = example.createCriteria();
		//如果价格为淡旺季类型，则传入购票日期，查询购票日期大于起始日期小于截止日期的数据
		criteria.andFtStartdateLessThanOrEqualTo(tccPrice.getFtStartdate());
		if (tccPrice.getFtEnddate() == null) {
			criteria.andFtEnddateGreaterThanOrEqualTo(tccPrice.getFtStartdate());
		} else {
			criteria.andFtEnddateGreaterThanOrEqualTo(tccPrice.getFtEnddate());
		}
		if (StringUtils.isNoneEmpty(tccPrice.getFsRestype()))
			criteria.andFsRestypeEqualTo(tccPrice.getFsRestype());
		if (StringUtils.isNoneEmpty(tccPrice.getFsResno()))
			criteria.andFsResnoEqualTo(tccPrice.getFsResno());
		if (StringUtils.isNoneEmpty(tccPrice.getFsCcno()))
			criteria.andFsCcnoEqualTo(tccPrice.getFsCcno());
		List<TCCPrice> tccPrices = tccPriceService.selectTCCPrice(example);
		return tccPrices;
    }
}
