package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TResCCExample;
import com.yttx.yttxps.model.TResCCKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TResCCMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int countByExample(TResCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByExample(TResCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByPrimaryKey(TResCCKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insert(TResCCKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insertSelective(TResCCKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	List<TResCCKey> selectByExample(TResCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TResCCKey record,
			@Param("example") TResCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESCC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExample(@Param("record") TResCCKey record,
			@Param("example") TResCCExample example);
}