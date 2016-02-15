package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.RoomMapper;
import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.service.IRoomService;
import com.yttx.yttxps.service.IPubService;

/**
 * @author Lonvoy
 * @createDate 2016年1月19日
 * @email me@lonvoy.com
 * 
 */
@Service("roomService")
public class RoomService implements IRoomService {

	@Autowired
	private IPubService<Room> pubService;
	
	@Autowired
	private RoomMapper<Room> roomMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return roomMapper.selectCountSelective(map);
	}

	@Override
	public List<Room> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, roomMapper);
	}
	
	@Override
	public List<Room> selectSelective(Map<String, Object> map) {
		return roomMapper.selectSelective(map);
	}

	@Override
	public int insert(Room record) {
		return roomMapper.insert(record);
	}

	@Override
	public int update(Room record) {
		return roomMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(BigDecimal index) {
		return roomMapper.deleteByPrimaryKey(index);
	}

	@Override
	public Room selectRoomInfo(BigDecimal index) {
		return roomMapper.selectByPrimaryKey(index);
	}

	public BigDecimal selectSequence(){
		return roomMapper.selectSequence();
	}
}
