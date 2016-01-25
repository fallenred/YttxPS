package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TRouteCCExample;
import com.yttx.yttxps.model.TRouteCCKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRouteCCMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int countByExample(TRouteCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByExample(TRouteCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByPrimaryKey(TRouteCCKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insert(TRouteCCKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insertSelective(TRouteCCKey record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	List<TRouteCCKey> selectByExample(TRouteCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TRouteCCKey record,
			@Param("example") TRouteCCExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TROUTECC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExample(@Param("record") TRouteCCKey record,
			@Param("example") TRouteCCExample example);
}