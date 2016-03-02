package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TResourceScenicMapper;
import com.yttx.yttxps.mapper.TRestaurantMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TResourceScenic;
import com.yttx.yttxps.model.TResourceScenicExample;
import com.yttx.yttxps.model.TResourceScenicExample.Criteria;
import com.yttx.yttxps.model.TRestaurant;
import com.yttx.yttxps.model.vo.RestaurantPriceReq;
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
	
	@Autowired
	private TResourceScenicMapper<TResourceScenic> resourceScenicMapper ;

	/**
	 * 分页数据查询
	 */
	@Override
	public List<TRestaurant> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, restaurantMapper);
	}
	
	/**
	 * 查询餐厅列表
	 * add by huangtao
	 * 2012-02-23
	 */
	@Override
	public List<TRestaurant> selectRestaurant(Map<String, Object> map) {
		return restaurantMapper.selectRestaurant(map);
	}

	/**
	 * 新增一个餐厅
	 */
	@Override
	public boolean addRestaurent(TRestaurant restaurant){
		//新增景区资源对照数据
		String no = "ct"+produceNo();
		restaurant.setNo(no);
		restaurantMapper.insertSelective(restaurant);//向数据库中插入数据
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFiIndex(BigDecimal.valueOf(resourceScenicMapper.getSeq()));
		resourceScenic.setFsResno(restaurant.getNo());
		resourceScenic.setFsRestype("ct");
		resourceScenic.setFsScenicno(restaurant.getScenicNo());
		resourceScenicMapper.insert(resourceScenic);
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
		//更新TResourceMapper这张表
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFsResno(restaurant.getNo());
		resourceScenic.setFsScenicno(restaurant.getScenicNo());
		
		TResourceScenicExample example= new TResourceScenicExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsResnoEqualTo(restaurant.getNo());
	
		//example.getOredCriteria().add(new cr)
		resourceScenicMapper.updateByExampleSelective(resourceScenic, example);
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
		//删除景区资源对照表的数据
		TResourceScenicExample example= new TResourceScenicExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsResnoEqualTo(no);
		resourceScenicMapper.deleteByExample(example);
		//删除本条记录
		restaurantMapper.deleteByPrimaryKey(no);
		return true;
	}
	
	/*
	 * 生成一个餐厅编号
	 */
	private String produceNo() {
		int  seq =restaurantMapper.selectNo();
		return String.format("%08d", seq);
	}

	/**
	 * 提交餐厅价格选型
	 */
	@Override
	public boolean submitPrice(RestaurantPriceReq rpReq) {
		List<TCCPrice> tPrices =rpReq.getPrices();
		for(TCCPrice price:tPrices){
			price.setFsRestype("ct");
			price.setFsResno(rpReq.getNo());
			price.setFtStartdate(rpReq.getStartDate());
			price.setFtEnddate(rpReq.getEndDate());
			tCCPriceMapper.insertPrice(price);
			if("ERROR".equalsIgnoreCase(price.getExcuteCode())){
				throw new RuntimeException(price.getErrorMsg());
			}
		}
		return true;
	}
}
