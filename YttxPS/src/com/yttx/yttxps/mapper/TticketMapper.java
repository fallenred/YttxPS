package com.yttx.yttxps.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;

public interface TticketMapper<T> extends IBaseMapper<T> {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int countByExample(TticketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int deleteByExample(TticketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int deleteByPrimaryKey(String fsNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int insert(Tticket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int insertSelective(Tticket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	List<Tticket> selectByExample(TticketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	Tticket selectByPrimaryKey(String fsNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Tticket record,
			@Param("example") TticketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int updateByExample(@Param("record") Tticket record,
			@Param("example") TticketExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int updateByPrimaryKeySelective(Tticket record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTICKET
	 * @mbggenerated  Mon Jan 18 22:22:22 CST 2016
	 */
	int updateByPrimaryKey(Tticket record);

    int selectFsNo();
}