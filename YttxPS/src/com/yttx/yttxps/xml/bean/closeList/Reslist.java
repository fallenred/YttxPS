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
public class Reslist {

	/**
	 * 编号
	 */
	@XStreamAlias("resno")
	private String resno;
	
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
	private String unitprice;
	
	/**
	 * 数量
	 */
	@XStreamAlias("number")
	private String number;
	
	/**
	 * 总价
	 */
	@XStreamAlias("totalprice")
	private String totalprice;
	
	/**
	 * 宾馆编号
	 */
	@XStreamAlias("accomno")
	private String accomno;
	
	/**
	 * 房型编号 字典表中房型编号
	 */
	@XStreamAlias("roomtypeno")
	private String roomtypeno;
	
	/**
	 * 房间类型名
	 */
	@XStreamAlias("roomtype")
	private String roomtype;
	
	/**
	 * 门票类型名
	 */
	@XStreamAlias("typename")
	private String typename;
	
	/**
	 * 人数
	 */
	@XStreamAlias("people")
	private String people;
	
	/**
	 * 人头返款总额
	 */
	@XStreamAlias("peopleprofit")
	private String peopleprofit;
	
	/**
	 * 打单总金额
	 */
	@XStreamAlias("totalconsp")
	private String totalconsp;
	
	/**
	 * 总利润
	 */
	@XStreamAlias("totalprofit")
	private String totalprofit;
	
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
	private String typeno;
	
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

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
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

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getAccomno() {
		return accomno;
	}

	public void setAccomno(String accomno) {
		this.accomno = accomno;
	}

	public String getRoomtypeno() {
		return roomtypeno;
	}

	public void setRoomtypeno(String roomtypeno) {
		this.roomtypeno = roomtypeno;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPeopleprofit() {
		return peopleprofit;
	}

	public void setPeopleprofit(String peopleprofit) {
		this.peopleprofit = peopleprofit;
	}

	public String getTotalconsp() {
		return totalconsp;
	}

	public void setTotalconsp(String totalconsp) {
		this.totalconsp = totalconsp;
	}

	public String getTotalprofit() {
		return totalprofit;
	}

	public void setTotalprofit(String totalprofit) {
		this.totalprofit = totalprofit;
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

	public String getTypeno() {
		return typeno;
	}

	public void setTypeno(String typeno) {
		this.typeno = typeno;
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
