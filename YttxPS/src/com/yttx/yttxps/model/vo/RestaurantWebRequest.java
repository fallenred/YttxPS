package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.TRestaurant;

/**
 * 类描述：餐厅资源配置--餐厅列表--分页请求类
 *@author sunchao
 *@date 2016年2月16日 上午11:24:53
 */
public class RestaurantWebRequest extends JqGridRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private TRestaurant restaurant = new TRestaurant();

	public TRestaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(TRestaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void copyTRestaurant(Map<String, Object> map){
		if(restaurant!=null){
			map.put("name", restaurant.getName());
			map.put("regionno", restaurant.getRegionno());
			map.put("minScale",restaurant.getMinScale());
			map.put("maxScale",restaurant.getMaxScale());
			map.put("special",restaurant.getSpecial());
			map.put("lvl",restaurant.getLvl());
			map.put("stat",restaurant.getStat());
		}
	}	
}
