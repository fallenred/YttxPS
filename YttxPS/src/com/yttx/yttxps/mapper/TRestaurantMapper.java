package com.yttx.yttxps.mapper;

import java.util.List;

import com.yttx.yttxps.model.TRestaurant;

public interface TRestaurantMapper<T> extends IBaseMapper<T>{
	
	/**
	 * 找到满足特定状态的餐厅
	 * stat=1：找到所有状态为正常的餐厅
	 * stat=2：找到所有状态为失效的餐厅
	 * stat=null：找到所有的餐厅
	 */
	List<TRestaurant> findRestaurantByStat(Long stat);
	
    /**
     * 通过主键删除一个记录
     */
    int deleteByPrimaryKey(String no);

    /**
     * 插入一条记录
     */
    int insert(TRestaurant record);

    /**
     * 动态的插入一条记录
     */
    int insertSelective(TRestaurant record);

    /**
     * 通过主键获取餐厅信息
     */
    TRestaurant selectByPrimaryKey(String no);

    /**
     * 动态更新餐厅信息(餐厅属性不为空或不为''的更新)
     */
    int updateByPrimaryKeySelective(TRestaurant record);

    /**
     * 更新一个餐厅信息
     */
    int updateByPrimaryKey(TRestaurant record);
    
    /**
     * 从序列中获取一个数字用来生成餐厅编号
     */
    int selectNo();
}