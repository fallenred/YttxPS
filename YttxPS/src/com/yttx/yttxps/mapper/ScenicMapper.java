package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.Scenic;

public interface ScenicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TSCENIC
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String fsNo);

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
    Scenic selectByPrimaryKey(String fsNo);

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
}