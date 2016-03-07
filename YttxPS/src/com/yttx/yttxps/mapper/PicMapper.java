package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.Pic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PicMapper<T> extends IBaseMapper<T>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigDecimal index);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    int insert(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    int insertSelective(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    Pic selectByPrimaryKey(BigDecimal index);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Pic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TPIC
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Pic record);
    
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
    
	BigDecimal selectSequence();

	/**
	 * 找到resNo下所有图片
	 */
	List<Pic> findByResNo(String resNo);
}