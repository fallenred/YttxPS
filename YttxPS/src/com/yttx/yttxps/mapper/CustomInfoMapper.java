package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.CustomInfo;

public interface CustomInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    int insert(CustomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    int insertSelective(CustomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    CustomInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CustomInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TCUSTOMINFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CustomInfo record);
}