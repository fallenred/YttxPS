package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TRemarks;
import com.yttx.yttxps.model.TRemarksExample;
import com.yttx.yttxps.model.TRemarksKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRemarksMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int countByExample(TRemarksExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int deleteByExample(TRemarksExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int deleteByPrimaryKey(TRemarksKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int insert(TRemarks record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int insertSelective(TRemarks record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	List<TRemarks> selectByExample(TRemarksExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	TRemarks selectByPrimaryKey(TRemarksKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TRemarks record,
			@Param("example") TRemarksExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int updateByExample(@Param("record") TRemarks record,
			@Param("example") TRemarksExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int updateByPrimaryKeySelective(TRemarks record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TREMARKS
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	int updateByPrimaryKey(TRemarks record);
}