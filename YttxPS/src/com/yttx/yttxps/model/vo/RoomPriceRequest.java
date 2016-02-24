package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.RoomPrice;

public class RoomPriceRequest extends JqGridRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4900996216647509350L;

	private RoomPrice roomPrice;

	public RoomPrice getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(RoomPrice roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	public void copyRoom(Map<String, Object> map) {
		if (roomPrice != null) {
			map.put("fsAccomno", roomPrice.getFsAccomno());   //宾馆代码
			map.put("fsResno", roomPrice.getFsResno());   //房型代码
			map.put("fsRegionno", roomPrice.getFsRegionno());   //所属地区编号
			map.put("fsTacname", roomPrice.getFsTacname());   //酒店名称
			map.put("fsRoomtype", roomPrice.getFsRoomtype());   //酒店名称
			map.put("fsRoomname", roomPrice.getFsRoomname());   //房型名称
			map.put("fiRoomstat", roomPrice.getFiRoomstat());   //状态
		}
	}
}
