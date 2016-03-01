package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.model.vo.RestaurantPriceReq;

/**
 * 类描述：餐厅资源配置Service
 * @author sunchao
 * @date 2016年2月16日 上午10:35:19
 */
public interface IRestaurantService {
	/**
	 * 查询某一页的数据
	 * @param map
	 * @return
	 */
	@Transactional(readOnly = true)
	List<TRestaurant> selectSelectivePage(Map<String, Object> map);

	/**
	 * 添加一个餐厅
	 * @param TRestaurant restaurant
	 * @return 
	 */
	@Transactional
	boolean addRestaurent(TRestaurant restaurant);
	
	/**
	 * 查询餐厅信息
	 * @param String no：餐厅编号
	 * @return TRestaurant
	 */
	@Transactional(readOnly = true)
	TRestaurant selectRestaurantInfo(String no);

	/**
	 * 查询餐厅信息
	 * @param TRestaurant restaurant
	 * @return boolean
	 */
	@Transactional
	boolean updateRestaurent(TRestaurant restaurant);

	/**
	 * 删除餐厅信息
	 * @param String no
	 * @return boolean
	 */
	@Transactional
	boolean deleteRestaurant(String no);

	/**
	 * 提交餐厅价格
	 * @param String no
	 * @return boolean
	 */
	@Transactional
	boolean submitPrice(RestaurantPriceReq rpReq);
	
	/**
	 * 查询餐厅列表
	 * @param map
	 * @return
	 */
	@Transactional(readOnly = true) 
	List<TRestaurant> selectRestaurant(Map<String, Object> map);
}
