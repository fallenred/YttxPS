package com.yttx.yttxps.xml.bean.closeList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 成本明细
 * @author admin
 *
 */
@XStreamAlias("costdetails")
public class CostDetails {

	/**
	 * 餐
	 */
	@XStreamAlias("restaurant")
	private Restaurant restaurant;
	
	/**
	 * 房
	 */
	@XStreamAlias("accomadation")
	private Accomadation accomadation;
	
	/**
	 * 车
	 */
	@XStreamAlias("car")
	private Car car;
	
	/**
	 * 门票
	 */
	@XStreamAlias("ticket")
	private Ticket ticket;
	
	/**
	 * 其他支出
	 */
	@XStreamAlias("other")
	private Other other;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Accomadation getAccomadation() {
		return accomadation;
	}

	public void setAccomadation(Accomadation accomadation) {
		this.accomadation = accomadation;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Other getOther() {
		return other;
	}

	public void setOther(Other other) {
		this.other = other;
	}
	
}
