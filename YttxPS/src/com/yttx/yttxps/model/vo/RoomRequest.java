package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Room;

/**
 * 房型和视图映射
 * 
 * @author Lonvoy
 * @createDate 2016年1月19日
 * @email me@lonvoy.com
 * 
 */
public class RoomRequest extends JqGridRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	private Room room;

	public void copyRoom(Map<String, Object> map) {
		if (room != null) {
			map.put("index", room.getIndex());
			map.put("accomno", room.getAccomno());
			map.put("meal", room.getMeal());
			map.put("price", room.getPrice());
			map.put("type", room.getType());
			map.put("name", room.getName());
			map.put("stat", room.getStat());
		}
	}
}