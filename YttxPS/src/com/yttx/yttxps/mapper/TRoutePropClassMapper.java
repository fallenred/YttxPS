package com.yttx.yttxps.mapper;

import com.yttx.yttxps.model.TRoutePropClass;
import com.yttx.yttxps.model.TRoutePropClassExample;
import com.yttx.yttxps.model.TRoutePropClassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoutePropClassMapper {
    int countByExample(TRoutePropClassExample example);

    int deleteByExample(TRoutePropClassExample example);

    int deleteByPrimaryKey(TRoutePropClassKey key);

    int insert(TRoutePropClass record);

    int insertSelective(TRoutePropClass record);

    List<TRoutePropClass> selectByExample(TRoutePropClassExample example);

    TRoutePropClass selectByPrimaryKey(TRoutePropClassKey key);

    int updateByExampleSelective(@Param("record") TRoutePropClass record, @Param("example") TRoutePropClassExample example);

    int updateByExample(@Param("record") TRoutePropClass record, @Param("example") TRoutePropClassExample example);

    int updateByPrimaryKeySelective(TRoutePropClass record);

    int updateByPrimaryKey(TRoutePropClass record);
}