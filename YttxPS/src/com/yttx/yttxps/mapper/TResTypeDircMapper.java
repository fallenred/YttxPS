package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TResTypeDirc;
import com.yttx.yttxps.model.TResTypeDircExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TResTypeDircMapper {
    int countByExample(TResTypeDircExample example);

    int deleteByExample(TResTypeDircExample example);

    int deleteByPrimaryKey(String fsRestype);

    int insert(TResTypeDirc record);

    int insertSelective(TResTypeDirc record);

    List<TResTypeDirc> selectByExample(TResTypeDircExample example);

    TResTypeDirc selectByPrimaryKey(String fsRestype);

    int updateByExampleSelective(@Param("record") TResTypeDirc record, @Param("example") TResTypeDircExample example);

    int updateByExample(@Param("record") TResTypeDirc record, @Param("example") TResTypeDircExample example);

    int updateByPrimaryKeySelective(TResTypeDirc record);

    int updateByPrimaryKey(TResTypeDirc record);
}