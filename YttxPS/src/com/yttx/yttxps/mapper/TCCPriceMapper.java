package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.ResoucePrice;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCCPriceMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int countByExample(TCCPriceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int deleteByExample(TCCPriceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int deleteByPrimaryKey(TCCPriceKey key);
	
	/**
	 * 删除某一类资源下某个资源的所有信息
	 */
	int deleteByResTypeAndNo(@Param("resType") String resType,@Param("resNo") String resNo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int insert(TCCPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int insertSelective(TCCPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	List<TCCPrice> selectByExample(TCCPriceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	TCCPrice selectByPrimaryKey(TCCPriceKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int updateByExampleSelective(@Param("record") TCCPrice record,
			@Param("example") TCCPriceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int updateByExample(@Param("record") TCCPrice record,
			@Param("example") TCCPriceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int updateByPrimaryKeySelective(TCCPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TCCPRICE
	 * @mbggenerated  Sun Jan 24 18:25:21 CST 2016
	 */
	int updateByPrimaryKey(TCCPrice record);
	
	/**
	 * 查询某一天的价格
	 */
	List<ResoucePrice> selectOneDaySelective(ResoucePrice resQue);
	
	/**
	 * 资源定价
	 */
	List<ResoucePrice> insertPrice(TCCPrice price);
}