package com.yttx.yttxps.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.TEntertainment;
import com.yttx.yttxps.model.TEntertainmentExample;

public interface TEntertainmentMapper<T> extends IBaseMapper<T> {
    int countByExample(TEntertainmentExample example);

    int deleteByExample(TEntertainmentExample example);

    int deleteByPrimaryKey(String fsNo);

    int insert(TEntertainment record);

    int insertSelective(TEntertainment record);

    List<TEntertainment> selectByExample(TEntertainmentExample example);
    
    /**
     * 查询娱乐项目列表
     * add by huangtao
     * 2016-02-24
     * @param map
     * @return
     */
    List<TEntertainment> selectEntertainment(Map<String, Object> map);

    TEntertainment selectByPrimaryKey(String fsNo);

    int updateByExampleSelective(@Param("record") TEntertainment record, @Param("example") TEntertainmentExample example);

    int updateByExample(@Param("record") TEntertainment record, @Param("example") TEntertainmentExample example);

    int updateByPrimaryKeySelective(TEntertainment record);

    int updateByPrimaryKey(TEntertainment record);
    
    int selectFsNo();
    
    List<TEntertainment> selectEntertainmentDynamic(Map<String, Object> map);
}