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
	@XStreamAlias("resno")
	private String resNo;
	
	/**
	 * 名字
	 */
	@XStreamAlias("name")
	private String name;
	
	/**
	 * 类型
	 */
	@XStreamAlias("type")
	private String type;
	
	/**
	 * 日期
	 */
	@XStreamAlias("time")
	private String time;
	
	/**
	 * 单价
	 */
	@XStreamAlias("unitprice")
	private String unitPrice;
	
	/**
	 * 数量
	 */
	@XStreamAlias("number")
	private String number;
	
	/**
	 * 总价
	 */
	@XStreamAlias("totalprice")
	private String totalPrice;
	
	/**
	 * 宾馆编号
	 */
	@XStreamAlias("accomno")
	private String accomNo;
	
	/**
	 * 房型编号 字典表中房型编号
	 */
	@XStreamAlias("roomtypeno")
	private String roomTypeNo;
	
	/**
	 * 房间类型名
	 */
	@XStreamAlias("roomtype")
	private String roomType;
	
	/**
	 * 门票类型名
	 */
	@XStreamAlias("typename")
	private String typeName;
	
	/**
	 * 人数
	 */
	@XStreamAlias("people")
	private String people;
	
	/**
	 * 人头返款总额
	 */
	@XStreamAlias("peopleprofit")
	private String peopleProfit;
	
	/**
	 * 打单总金额
	 */
	@XStreamAlias("totalconsp")
	private String totalconsp;
	
	/**
	 * 总利润
	 */
	@XStreamAlias("totalprofit")
	private String totalProfit;
	
	/**
	 * 单人收取金额
	 */
	@XStreamAlias("consumption")
	private String consumption;
	
	/**
	 * 单人成本
	 */
	@XStreamAlias("cost")
	private String cost;
	
	/**
	 * 返佣比例(收取金额-成本)*返佣比例=利润
	 */
	@XStreamAlias("proportion")
	private String proportion;
	
	
	/**
	 * 项目编号
	 */
	@XStreamAlias("typeno")
	private String typeNo;
	
	/**
	 * 备注
	 */
	@XStreamAlias("remark")
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

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
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

	public String getTotalconsp() {
		return totalconsp;
	}

	public void setTotalconsp(String totalconsp) {
		this.totalconsp = totalconsp;
	}
	
}
