package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TResourceScenic;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yttx.yttxps.model.TResourceScenicExample;

public interface TResourceScenicMapper<T> extends IBaseMapper<T>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int countByExample(TResourceScenicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int deleteByExample(TResourceScenicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int deleteByPrimaryKey(BigDecimal fiIndex);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int insert(TResourceScenic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int insertSelective(TResourceScenic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	List<TResourceScenic> selectByExample(TResourceScenicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	TResourceScenic selectByPrimaryKey(BigDecimal fiIndex);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TResourceScenic record,
			@Param("example") TResourceScenicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int updateByExample(@Param("record") TResourceScenic record,
			@Param("example") TResourceScenicExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int updateByPrimaryKeySelective(TResourceScenic record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TRESOURCESCENIC
	 * @mbggenerated  Sat Jan 16 11:00:29 CST 2016
	 */
	int updateByPrimaryKey(TResourceScenic record);
	
	/**
	 * 获取序列
	 * @return
	 */
	int getSeq();
}