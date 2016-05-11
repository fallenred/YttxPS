package com.yttx.yttxps.web.action.dict;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictKey;
import com.yttx.yttxps.model.vo.DictRequest;
import com.yttx.yttxps.service.IDictService;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("dict/")
public class DictController {
	static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IDictService dictService;

	/**
	 * 根据父节点查询字典项
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectDict.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectDict(DictRequest req) {  
		DictExample example = new DictExample();
		req.copyExample(example);
		List<Dict> list = dictService.selectDict(example);
		return list;
    }
	
	/**
	 * 其他支出类型
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectDictExpense.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectExpense(DictRequest req) {
		List<DictKey> list = dictService.selectDictByParentNo("expense");
		return list;
    }
	
	/**
	 * 其他收入类型
	 * @param req
	 * @return
	 */
	@RequestMapping(value="selectDictIncome.htm", method = RequestMethod.GET)
	@ResponseBody
	public Object ajaxSelectIncome(DictRequest req) {
		List<DictKey> list = dictService.selectDictByParentNo("income");
		return list;
    }
	
}
