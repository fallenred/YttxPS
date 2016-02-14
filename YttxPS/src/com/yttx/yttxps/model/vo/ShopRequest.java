package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TshopExample;
import com.yttx.yttxps.model.Tshop;
import com.yttx.yttxps.model.TshopExample.Criteria;

public class ShopRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tshop getShop() {
		return shop;
	}

	public void setShop(Tshop shop) {
		this.shop = shop;
	}

	private Tshop shop;

	public void copyShop(Map<String, Object> map) {
		if (shop != null) {
			map.put("no", shop.getNo() == null ? "" : shop.getNo());
			map.put("regionno",shop.getRegionno() == null ? "" : shop.getRegionno());
			map.put("name", shop.getName() == null ? "" : shop.getName());
			map.put("desc", shop.getDesr() == null ? "" : shop.getDesr());
			map.put("opentime",shop.getOpentime() == null ? "" : shop.getOpentime());
			map.put("tel", shop.getTel()== null ? "" : shop.getTel());
			map.put("singlereturn", shop.getSinglereturn() == null ? "" : shop.getSinglereturn());
			map.put("totalreturn", shop.getTotalreturn()== null ? 1 : shop.getTotalreturn());
			map.put("mantip",shop.getMantip() == null ? "" : shop.getMantip());
			map.put("parktip",shop.getParktip() == null ? "" : shop.getParktip());
			map.put("staytime",shop.getStaytime() == null ? "" : shop.getStaytime());
			map.put("policy",shop.getPolicy() == null ? "" : shop.getPolicy());
			map.put("stat",shop.getStat() == null ? "" : shop.getStat());
		}
	}
	public void copyTshop(TshopExample example) {
		if (shop != null) {
			Criteria criteria = example.createCriteria();
			//if (shop.getLvl() != null)
			//	criteria.andFsLvlEqualTo(shop.getLvl());
		}
	}
}