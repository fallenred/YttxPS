package com.yttx.yttxps.mapper;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> e676f2094b2536cce8e7d215d00f4e564100e959

import com.yttx.yttxps.model.Tguide;
import com.yttx.yttxps.model.TguideExample;

public interface TguideMapper<T> extends IBaseMapper<T>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    int insert(Tguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    int insertSelective(Tguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    Tguide selectByPrimaryKey(String no);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    List<Tguide> selectByExample(TguideExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Tguide record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGUIDE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Tguide record);
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