package com.yttx.yttxps.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yttx.yttxps.model.TResCCExample;
import com.yttx.yttxps.model.TResCCKey;

public interface TResCCMapper {

	int countByExample(TResCCExample example);

	int deleteByExample(TResCCExample example);

	int deleteByPrimaryKey(TResCCKey key);

	int insert(TResCCKey record);

	int insertSelective(TResCCKey record);

	List<TResCCKey> selectByExample(TResCCExample example);

	int updateByExampleSelective(@Param("record") TResCCKey record,@Param("example") TResCCExample example);

	int updateByExample(@Param("record") TResCCKey record,@Param("example") TResCCExample example);
	
	List<TResCCKey> selectTResCC(Map<String, Object> map);
}