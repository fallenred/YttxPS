package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TCCDirc;
import com.yttx.yttxps.model.TCCDircExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCCDircMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int countByExample(TCCDircExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByExample(TCCDircExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int deleteByPrimaryKey(String fsCcno);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insert(TCCDirc record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int insertSelective(TCCDirc record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	List<TCCDirc> selectByExample(TCCDircExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	TCCDirc selectByPrimaryKey(String fsCcno);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TCCDirc record,
			@Param("example") TCCDircExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByExample(@Param("record") TCCDirc record,
			@Param("example") TCCDircExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByPrimaryKeySelective(TCCDirc record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCDIRC
	 * @mbggenerated  Tue Jan 19 23:27:48 CST 2016
	 */
	int updateByPrimaryKey(TCCDirc record);
}