package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.Tgen;
import java.math.BigDecimal;

public interface TgenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(BigDecimal index);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    int insert(Tgen record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    int insertSelective(Tgen record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    Tgen selectByPrimaryKey(BigDecimal index);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Tgen record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TGEN
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Tgen record);
}