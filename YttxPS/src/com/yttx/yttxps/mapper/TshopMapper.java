package com.yttx.yttxps.mapper;

import java.util.List;

import java.util.Map;

import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.Tshop;
import com.yttx.yttxps.model.TshopExample;

public interface TshopMapper<T> extends IBaseMapper<T> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int insert(Tshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int insertSelective(Tshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    Scenic selectByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Tshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Tshop record);
    
    List<Tshop> selectByExample(TshopExample example);
    /**
     * 
     * @param record
     * @return
     */
    int selectCountSelective(Map<String, Object> map);
    
    /**
     * @param map
     * @return List<Object>
     */
    List<T> selectSelectivePage(Map<String, Object> map);
}