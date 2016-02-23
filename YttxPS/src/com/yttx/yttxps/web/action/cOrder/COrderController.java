package com.yttx.yttxps.web.action.cOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yttx.yttxps.web.action.BaseController;

/**
 * 类描述：结算管理的Controller
 * @author sunchao
 * @date 2016年2月22日 下午12:55:01
 */
@Controller
@Scope("prototype")
@RequestMapping("cOrder/")
public class COrderController extends BaseController {
	
	static Logger logger = LoggerFactory.getLogger(COrderController.class);
	
	/**
	 * 打开结算单管理的界面
	 */
	@RequestMapping(value="page.htm")
	public String openPage(){
		return "cOrder/corder";
	}
	
	
	
	
	
}
