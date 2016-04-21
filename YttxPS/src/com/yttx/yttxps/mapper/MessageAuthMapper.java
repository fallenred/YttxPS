package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.MessageAuth;
import com.yttx.yttxps.model.MessageAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int countByExample(MessageAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int deleteByExample(MessageAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int insert(MessageAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int insertSelective(MessageAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    List<MessageAuth> selectByExample(MessageAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    MessageAuth selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int updateByExampleSelective(@Param("record") MessageAuth record, @Param("example") MessageAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int updateByExample(@Param("record") MessageAuth record, @Param("example") MessageAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int updateByPrimaryKeySelective(MessageAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TMESSAGEAUTH
     *
     * @mbggenerated Mon Apr 18 16:35:52 CST 2016
     */
    int updateByPrimaryKey(MessageAuth record);
}