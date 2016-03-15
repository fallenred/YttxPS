package com.yttx.yttxps.model.corder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yttx.yttxps.xml.SnapshotUtil;

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
	
	//资源快照解析出响应的对象
	private List<Map<String, List<Map<String, Object>>>> resList;

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

	public List<Map<String, List<Map<String, Object>>>> getResList() {
		return SnapshotUtil.parseDayRes(resSnapshot);
	}

	public void setResList(List<Map<String, List<Map<String, Object>>>> resList) {
		this.resList = resList;
	}
}
