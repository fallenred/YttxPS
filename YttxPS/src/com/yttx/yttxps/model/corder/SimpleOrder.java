package com.yttx.yttxps.model.corder;

import java.math.BigDecimal;
import java.util.Date;

import com.yttx.comm.DateUtil;
/**
 * 类描述：简单的订单对象，用于分页列表等功能
 * @author sunchao
 * @date 2016年2月23日 下午2:33:48
 */
public class SimpleOrder {
	//	订单ID
	private String no;
	
	//所属线路统称Idx
	private Long genIndex;
	
	//订单名称
	private String name;	
	
	//用户ID
	private String userId;
	
	//用户子ID
	private String userSubId;
	
	//计调ID
	private String operId;
	
	//创建时间
	private Date createDate;
	
	private String createDateDesc;
	
	//线路类型
	private String type;
	
	//如果是衍生线路，则为TRouteArrange的ID
	private String routeId;	
	
	//01:独立成团；02:散客拼团
	private String property;
	
	//线路天数
	private Long days;
	
	//发团日期
	private Date startDate;
	
	private String startDateDesc;
	
	//发团地
	private String startPlace;
	
	private String startPlaceName;
	
	//非空	线路初始报价(单人)
	private BigDecimal price;	
	
	//线路摘要
	private String summary;	
	
	//日程快照
	private String schedule;
	
	//预估全价
	private BigDecimal totalFee;
	
	//已缴金额
	private BigDecimal paidAmt;
	
	//整体备注
	private String remark;		
	
	//订单状态
	private Long stat;
	
	//公共模糊字段
	protected String commFuzzySnapshot;
	
	//公共精确资源字段
	protected String commResSnapshot;
	
	private Integer visitorNum;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = (no==null?null:no.trim());
	}

	public Long getGenIndex() {
		return genIndex;
	}

	public void setGenIndex(Long genIndex) {
		this.genIndex = genIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = (name==null?null:name.trim());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = (userId==null?null:userId.trim());
	}

	public String getUserSubId() {
		return userSubId;
	}

	public void setUserSubId(String userSubId) {
		this.userSubId = (userSubId == null ? null : userSubId.trim());
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = (operId ==null ?null :operId.trim());
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = (type == null?null:type.trim());
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = (routeId == null?null:routeId.trim());
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = (property == null ? null:property.trim());
	}


	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = (startPlace == null? null:startPlace.trim());
	}

	public String getStartPlaceName() {
		return startPlaceName;
	}

	public void setStartPlaceName(String startPlaceName) {
		this.startPlaceName = startPlaceName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = (summary==null ?null:summary.trim());
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = (schedule==null?null:schedule.trim());
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = (remark == null?null:remark.trim());
	}

	public Long getStat() {
		return stat;
	}

	public void setStat(Long stat) {
		this.stat = stat;
	}

	public String getCommFuzzySnapshot() {
		return commFuzzySnapshot;
	}

	public void setCommFuzzySnapshot(String commFuzzySnapshot) {
		this.commFuzzySnapshot = (commFuzzySnapshot ==null?null:commFuzzySnapshot.trim());
	}

	public String getCommResSnapshot() {
		return commResSnapshot;
	}

	public void setCommResSnapshot(String commResSnapshot) {
		this.commResSnapshot = (commResSnapshot == null?null:commResSnapshot.trim());
	}
	

	public String getCreateDateDesc() {
		this.createDateDesc = DateUtil.getFullTimeFormatStr(createDate);
		return createDateDesc;
	}

	public void setCreateDateDesc(String createDateDesc) {
		this.createDateDesc = createDateDesc;
	}

	public String getStartDateDesc() {
		this.startDateDesc = DateUtil.getDateFormatStr(startDate);
		return startDateDesc;
	}

	public void setStartDateDesc(String startDateDesc) {
		this.startDateDesc = startDateDesc;
	}
	

	public Integer getVisitorNum() {
		return visitorNum;
	}

	public void setVisitorNum(Integer visitorNum) {
		this.visitorNum = visitorNum;
	}

	@Override
	public String toString() {
		return "SimpleOrder [no=" + no + ", genIndex=" + genIndex + ", name=" + name + ", userId=" + userId
				+ ", userSubId=" + userSubId + ", operId=" + operId + ", createDate=" + createDate + ", type=" + type
				+ ", routeId=" + routeId + ", property=" + property + ", days=" + days + ", startDate=" + startDate
				+ ", startPlace=" + startPlace + ", price=" + price + ", summary=" + summary + ", schedule=" + schedule
				+ ", totalFee=" + totalFee + ", paidAmt=" + paidAmt + ", remark=" + remark + ", stat=" + stat
				+ ", commFuzzySnapshot=" + commFuzzySnapshot + ", commResSnapshot=" + commResSnapshot + "]";
	}	
}
