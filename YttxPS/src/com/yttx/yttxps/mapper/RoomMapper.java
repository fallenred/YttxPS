package com.yttx.yttxps.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.model.RoomExample;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper<T> extends IBaseMapper<T> {
    int countByExample(RoomExample example);

	int deleteByExample(RoomExample example);

	int deleteByPrimaryKey(String fsRoomno);

	int insert(Room record);

	int insertSelective(Room record);

	List<Room> selectByExample(RoomExample example);

	Room selectByPrimaryKey(String fsRoomno);

	int updateByExampleSelective(@Param("record") Room record,@Param("example") RoomExample example);

	int updateByExample(@Param("record") Room record,@Param("example") RoomExample example);

	int updateByPrimaryKeySelective(Room record);

	int updateByPrimaryKey(Room record);

	BigDecimal selectSequence();
    
	/**
	 * 查询酒店房型（线路配置时使用）
	 * add by huangtao
	 * 2016-02-14
	 * @param map
	 * @return
	 */
	List<Room> selectSelective(Map map);
}