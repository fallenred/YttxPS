package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.CustChgAu;

public interface CustChgAuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String auditno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    int insert(CustChgAu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    int insertSelective(CustChgAu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    CustChgAu selectByPrimaryKey(String auditno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CustChgAu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTCHGAU
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CustChgAu record);
}