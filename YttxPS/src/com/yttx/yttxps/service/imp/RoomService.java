package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.RoomMapper;
import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRoomService;

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
		record.setFsRoomno(String.format("%010d", roomMapper.selectSequence().intValue()));
		return roomMapper.insert(record);
	}

	@Override
	public int update(Room record) {
		return roomMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String fsRoomno) {
		return roomMapper.deleteByPrimaryKey(fsRoomno);
	}

	@Override
	public Room selectRoomInfo(String fsRoomno) {
		return roomMapper.selectByPrimaryKey(fsRoomno);
	}

	public BigDecimal selectSequence(){
		return roomMapper.selectSequence();
	}
}
