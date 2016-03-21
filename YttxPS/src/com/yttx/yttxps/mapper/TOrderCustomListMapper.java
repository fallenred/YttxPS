package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TOrderCustomList;
import com.yttx.yttxps.model.TOrderCustomListExample;
import com.yttx.yttxps.model.TOrderCustomListKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrderCustomListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int countByExample(TOrderCustomListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int deleteByExample(TOrderCustomListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int deleteByPrimaryKey(TOrderCustomListKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int insert(TOrderCustomList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int insertSelective(TOrderCustomList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    List<TOrderCustomList> selectByExample(TOrderCustomListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    TOrderCustomList selectByPrimaryKey(TOrderCustomListKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int updateByExampleSelective(@Param("record") TOrderCustomList record, @Param("example") TOrderCustomListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int updateByExample(@Param("record") TOrderCustomList record, @Param("example") TOrderCustomListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int updateByPrimaryKeySelective(TOrderCustomList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TORDERCUSTOMLIST
     *
     * @mbggenerated Sun Mar 20 16:44:11 CST 2016
     */
    int updateByPrimaryKey(TOrderCustomList record);
}