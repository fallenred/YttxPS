package com.yttx.yttxps.model.corder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yttx.yttxps.xml.SnapshotUtil;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;


/**
 * 类描述：详细的订单对象
 * @author sunchao
 * @date 2016年2月23日 下午2:32:24
 */
public class DetailOrder extends SimpleOrder{
	
	private HashMap<String, String> resMap;
	
	//批次信息
	private List<CustomerBatch> batches;
	
	//订单备注
	private List<ORemark>  remarks;
	
	//游客信息
	private List<Tourist> tourists;

	
	public HashMap<String, String> getResMap() {
		return  parseCommRes(commResSnapshot);
	}


	public void setResMap(HashMap<String, String> resMap) {
		this.resMap = resMap;
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


	private HashMap<String, String>  parseCommRes(String xml){
		List<Reslist> commResList=SnapshotUtil.conver2ResList(xml);
		HashMap<String, String> resMap= SnapshotUtil.converResListToDisplay(commResList);
		return resMap;
	}
}
