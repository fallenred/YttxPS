package com.yttx.yttxps.model.corder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.yttx.yttxps.xml.SnapshotUtil;
import com.yttx.yttxps.xml.bean.Daylist;

/**
 * 类描述：每一批次客户的详细信息
 * @author sunchao
 * @date 2016年2月23日 下午3:16:25
 */
public class CustomerBatch{
	//自动序号
	private Long id;
	
	//订单ID
	private String orderId;
	
	//批次顺序号
	private Integer seq	;
	
	//1:团队客户  2:散客客户
	private String type;
	
	//联系人
	private String contactName;
	
	//联系电话
	private String contactTel;
	
	//总体人数
	private Integer total;
	
	//老人数
	private Integer older;
	
	//成年人数
	private Integer adult;
	
	//儿童数
	private Integer children;
	
	//附言
	private String postscript;
	
	//预估金额小计
	private BigDecimal amt;
	
	//资源模糊描述快照
	private String fuzzySnapshot;
	
	//资源快照
	private String resSnapshot;
	
	//客户列表
	private List<Customer> customers;
	
	//资源快照解析出响应的对象
	private List<HashMap<String, String>> resList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getOlder() {
		return older;
	}

	public void setOlder(Integer older) {
		this.older = older;
	}

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getFuzzySnapshot() {
		return fuzzySnapshot;
	}

	public void setFuzzySnapshot(String fuzzySnapshot) {
		this.fuzzySnapshot = fuzzySnapshot;
	}

	public String getResSnapshot() {
		return resSnapshot;
	}

	public void setResSnapshot(String resSnapshot) {
		this.resSnapshot = resSnapshot;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	
	public List<HashMap<String, String>> getResList() {
		return parseDailyRes(resSnapshot);
	}

	public void setResList(List<HashMap<String, String>> resList) {
		this.resList = resList;
	}

	private List<HashMap<String, String>> parseDailyRes(String xml){
		List<Daylist> daylists = SnapshotUtil.conver2DayList(xml);
		if(daylists!=null){
			Collections.sort(daylists,new Comparator<Daylist>(){
				@Override
				public int compare(Daylist o1, Daylist o2){
					return o1.getDayflag().compareTo(o2.getDayflag());
				}
			});
			List<HashMap<String, String>> list= new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map =null;
			for(Daylist daylist:daylists){
				map = SnapshotUtil.converResListToDisplay(daylist.getReslist());
				list.add(map);
			}
			return list;
		}
		return null;
	}
}
