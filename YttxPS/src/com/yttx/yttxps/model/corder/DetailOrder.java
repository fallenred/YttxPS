package com.yttx.yttxps.model.corder;

import java.util.List;
import java.util.Map;

import com.yttx.yttxps.xml.SnapshotUtil;


/**
 * 类描述：详细的订单对象
 * @author sunchao
 * @date 2016年2月23日 下午2:32:24
 */
public class DetailOrder extends SimpleOrder{
	
	private Map<String, List<Map<String, Object>>> resMap;
	
	private List<Map<String, List<Map<String, Object>>>> dayResList;
	
	//批次信息
	private List<CustomerBatch> batches;
	
	//订单备注
	private List<ORemark>  remarks;
	
	//游客信息
	private List<Tourist> tourists;

	public Map<String, List<Map<String, Object>>> getResMap() {
		return SnapshotUtil.parseCommRes(commResSnapshot);
	}

	public void setResMap(Map<String, List<Map<String, Object>>> resMap) {
		this.resMap = resMap;
	}

	public List<Map<String, List<Map<String, Object>>>> getDayResList() {
		return SnapshotUtil.parseDayRes(commResSnapshot);
	}

	public void setDayResList(List<Map<String, List<Map<String, Object>>>> dayResList) {
		this.dayResList = dayResList;
	}

	public List<CustomerBatch> getBatches() {
		return batches;
	}

	public void setBatches(List<CustomerBatch> batches) {
		this.batches = batches;
	}

	public List<ORemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<ORemark> remarks) {
		this.remarks = remarks;
	}

	public List<Tourist> getTourists() {
		return tourists;
	}

	public void setTourists(List<Tourist> tourists) {
		this.tourists = tourists;
	}
}
