package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.Taccomadation;

public interface TaccomadationMapper {
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
    int insert(Taccomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int insertSelective(Taccomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    Taccomadation selectByPrimaryKey(String no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Taccomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Taccomadation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TACCOMADATION
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Taccomadation record);
}