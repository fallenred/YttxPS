package com.yttx.yttxps.xml.bean.closeList;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 数据集
 * @author admin
 *
 */
@XStreamAlias("reslist")
public class ResultList {

	/**
	 * 编号
	 */
	@XStreamImplicit(itemFieldName="resno")
	private String resNo;
	
	/**
	 * 名字
	 */
	@XStreamImplicit(itemFieldName="name")
	private String name;
	
	/**
	 * 类型
	 */
	@XStreamImplicit(itemFieldName="type")
	private String type;
	
	/**
	 * 日期
	 */
	@XStreamImplicit(itemFieldName="time")
	private String time;
	
	/**
	 * 单价
	 */
	@XStreamImplicit(itemFieldName="unitprice")
	private String unitPrice;
	
	/**
	 * 数量
	 */
	@XStreamImplicit(itemFieldName="number")
	private String number;
	
	/**
	 * 总价
	 */
	@XStreamImplicit(itemFieldName="totalprice")
	private String totalPrice;
	
	/**
	 * 宾馆编号
	 */
	@XStreamImplicit(itemFieldName="accomno")
	private String accomNo;
	
	/**
	 * 房型编号 字典表中房型编号
	 */
	@XStreamImplicit(itemFieldName="roomtypeno")
	private String roomTypeNo;
	
	/**
	 * 房间类型名
	 */
	@XStreamImplicit(itemFieldName="roomtype")
	private String roomType;
	
	/**
	 * 门票类型名
	 */
	@XStreamImplicit(itemFieldName="typename")
	private String typeName;
	
	/**
	 * 人数
	 */
	@XStreamImplicit(itemFieldName="people")
	private String people;
	
	/**
	 * 人头返款总额
	 */
	@XStreamImplicit(itemFieldName="peopleprofit")
	private String peopleProfit;
	
	/**
	 * 总利润
	 */
	@XStreamImplicit(itemFieldName="totalprofit")
	private String totalProfit;
	
	/**
	 * 收取金额
	 */
	@XStreamImplicit(itemFieldName="consumption")
	private String consumption;
	
	/**
	 * 成本
	 */
	@XStreamImplicit(itemFieldName="cost")
	private String cost;
	
	/**
	 * 备注
	 */
	@XStreamImplicit(itemFieldName="remark")
	private String remark;
	
	/**
	 * 购物明细
	 */
	@XStreamImplicit(itemFieldName="cclist")
	private List<Stuff> cclist;

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAccomNo() {
		return accomNo;
	}

	public void setAccomNo(String accomNo) {
		this.accomNo = accomNo;
	}

	public String getRoomTypeNo() {
		return roomTypeNo;
	}

	public void setRoomTypeNo(String roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPeopleProfit() {
		return peopleProfit;
	}

	public void setPeopleProfit(String peopleProfit) {
		this.peopleProfit = peopleProfit;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Stuff> getCclist() {
		return cclist;
	}

	public void setCclist(List<Stuff> cclist) {
		this.cclist = cclist;
	}
	
}
