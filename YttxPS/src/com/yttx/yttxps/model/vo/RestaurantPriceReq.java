package com.yttx.yttxps.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yttx.yttxps.model.TCCPrice;

/**
 *餐厅价格提交类
 *@author sunchao
 *@date 2016年2月19日 下午3:54:50
 */
public class RestaurantPriceReq {
	
	private String no;
	
	private Date startDate;
	
	private Date endDate;
	
	private List<TCCPrice> prices=new ArrayList<TCCPrice>();
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<TCCPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<TCCPrice> prices) {
		this.prices = prices;
	}
}
