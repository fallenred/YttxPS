package com.yttx.yttxps.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.Accomadation;

public interface AccomadationMapper<T>  extends IBaseMapper<T> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int insert(Accomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int insertSelective(Accomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    Accomadation selectByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Accomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Accomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Accomadation record);
    
    /**
	 * 查询宾馆列表（线路配置时使用）
	 * add by huangtao
	 * 2016-02-14
     * @param map
     * @return
     */
    List<T> selectSelective(Map<String, Object> map);
    
    BigDecimal selectSequence();
    
    List<T> selectSelectiveRgUnnecessary(Map<String, Object> map);
}