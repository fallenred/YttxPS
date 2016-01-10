package com.yttx.yttxps.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.TtransportArrangeKey;

public interface TtransportArrangeMapper<T> extends IBaseMapper<T>  {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int countByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int deleteByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int deleteByPrimaryKey(TtransportArrangeKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int insert(TtransportArrange record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int insertSelective(TtransportArrange record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	List<TtransportArrange> selectByExample(TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	TtransportArrange selectByPrimaryKey(TtransportArrangeKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TtransportArrange record,
			@Param("example") TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int updateByExample(@Param("record") TtransportArrange record,
			@Param("example") TtransportArrangeExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int updateByPrimaryKeySelective(TtransportArrange record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TTRANSPORTARRANGE
	 * @mbggenerated  Sun Jan 10 16:31:14 CST 2016
	 */
	int updateByPrimaryKey(TtransportArrange record);
}