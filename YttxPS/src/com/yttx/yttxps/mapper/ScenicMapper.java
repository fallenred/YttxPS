package com.yttx.yttxps.mapper;

import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.Scenic;

public interface ScenicMapper<T> extends IBaseMapper<T>{
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
    int insert(Scenic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int insertSelective(Scenic record);

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
    int updateByPrimaryKeySelective(Scenic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Scenic record);
    
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