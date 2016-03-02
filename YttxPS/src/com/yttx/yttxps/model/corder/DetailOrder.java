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
	
	/*//模糊资源快照解析
	private List<Reslist> fuzzyResList;
	
	//精确资源快照解析
	private List<Reslist> resList;*/
	private HashMap<String, String> resMap;
	
	//每一批次的客户
	private List<CustomerBatch> batches;
	
	//每个订单的备注
	private List<ORemark>  remarks;

	
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


	private HashMap<String, String>  parseCommRes(String xml){
		List<Reslist> commResList=SnapshotUtil.conver2ResList(xml);
		HashMap<String, String> resMap= SnapshotUtil.converResListToDisplay(commResList);
		return resMap;
	}
}
