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
			map.put("fsRoomno", room.getFsRoomno() != null ? room.getFsRoomno().trim() : "");   //序号
			map.put("fsAccomno", room.getFsAccomno() != null ? room.getFsAccomno().trim() : "");   //宾馆代码
			map.put("fsMeal", room.getFsMeal());   //早中晚三餐情况
			map.put("fsType", room.getFsType());   //房间类型
			map.put("fsName", room.getFsName());   //房型名称
			map.put("fiStat", room.getFiStat());   //状态
		}
	}
}