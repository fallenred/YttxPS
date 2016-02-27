package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.FStatementFilters;

/**
 * 类描述：封装页面请求结算单列表的数据
 * @author sunchao
 * @date 2016年2月23日 下午4:14:21
 */
public class FStatementPageRequest extends JqGridRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FStatementFilters fstatFilters = new FStatementFilters();
	

	public FStatementFilters getFstatFilters() {
		return fstatFilters;
	}


	public void setFstatFilters(FStatementFilters fstatFilters) {
		this.fstatFilters = fstatFilters;
	}


	/**
	 * 将过滤条件放入map中
	 */
	public void copyFStatementFilters(Map<String,Object> map){
		if(fstatFilters!=null){
			map.put("statementId",fstatFilters.getStatementId());
			map.put("orderId",fstatFilters.getOrderId());
			map.put("userId",fstatFilters.getUserId());
			map.put("operId",fstatFilters.getOperId());
			map.put("startDate",fstatFilters.getStartDate());
			map.put("endDate",fstatFilters.getEndDate());
			map.put("stat",fstatFilters.getStat());
		}
	}
}
