package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TRestaurantMapper;
import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRestaurantService;


/**
 * 类描述：餐厅资源配置Service实现类
 * @author sunchao
 * @date 2016年2月16日 上午10:35:19
 */
@Service("restaurantService")
public class RestaurantService implements IRestaurantService{
	
	@Autowired
	private IPubService<TRestaurant> pubService;
	
	@Autowired
	private TRestaurantMapper<TRestaurant> restaurantMapper;
	
	@Autowired
	private TCCPriceMapper tCCPriceMapper;

	/**
	 * 分页数据查询
	 */
	@Override
	public List<TRestaurant> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, restaurantMapper);
	}

	/**
	 * 新增一个餐厅
	 */
	@Override
	public boolean addRestaurent(TRestaurant restaurant){
		String no = produceNo();//生成一个部门编号
		restaurant.setNo(no);
		restaurantMapper.insertSelective(restaurant);//向数据库中插入数据
		return true;
	}

	/**
	 * 根据餐厅编号获取餐厅详细信息
	 */
	@Override
	public TRestaurant selectRestaurantInfo(String no) {
		return restaurantMapper.selectByPrimaryKey(no);
	}
	
	/**
	 * 更新餐厅信息
	 */
	@Override
	public boolean updateRestaurent(TRestaurant restaurant) {
		restaurantMapper.updateByPrimaryKeySelective(restaurant);
		return true;
	}
	
	/**
	 * 删除餐厅信息
	 */
	@Override
	public boolean deleteRestaurant(String no) {
		//删除资源表中对应的数据
		tCCPriceMapper.deleteByResTypeAndNo("ct",no);
		//TODO 是否删除资源服务器上的图片
		//删除本条记录
		restaurantMapper.deleteByPrimaryKey(no);
		return true;
	}
	
	/*
	 * 生成一个餐厅编号
	 */
	private String produceNo() {
		StringBuilder no = new StringBuilder("ct");
		String  seq = ""+restaurantMapper.selectNo();
		int length = seq.length();
		if(length<8){
			for(int i=0;i<8-length;i++){
				no.append("0");
			}
		}
		no.append(seq);
		return no.toString();
	}

	
}
