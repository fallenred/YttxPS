package com.yttx.yttxps.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.TtransportArrangeKey;

public interface TtransportArrangeMapper<T> extends IBaseMapper<T>  {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int countByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int deleteByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int deleteByPrimaryKey(TtransportArrangeKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int insert(TtransportArrangeKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int insertSelective(TtransportArrangeKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	List<TtransportArrangeKey> selectByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TtransportArrangeKey record,
			@Param("example") TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 31 15:49:49 CST 2016
	 */
	int updateByExample(@Param("record") TtransportArrangeKey record,
			@Param("example") TtransportArrangeExample example);

	int selectFsNo();
	
	List<TtransportArrange> selectTtransportArrangeByExample(TtransportArrangeExample example);
	
	TtransportArrangeKey selectByPrimaryKey(String fsNo);
}