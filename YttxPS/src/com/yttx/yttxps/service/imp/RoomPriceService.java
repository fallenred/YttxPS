package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.RoomPriceMapper;
import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.model.RoomPrice;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.service.IRoomPriceService;

@Service("roomPriceService")
public class RoomPriceService implements IRoomPriceService {
	
	@Autowired
	private RoomPriceMapper<RoomPrice> roomPriceMapper;
	
	@Autowired
	private TCCPriceMapper priceMapper;

	@Override
	public List<RoomPrice> selectSelectivePage(Map<String, Object> map) {
		return roomPriceMapper.selectSelectivePage(map);
	}

	@Override
	public void insertRoomPrice(TCCPrice price) {
		price.setFsCcno("000024");   //房间消费
		price.setFsRestype("bg");   //宾馆
		priceMapper.insertPrice(price);
	}

	@Override
	public void updateRoomPrice(TCCPrice price) {
		priceMapper.updateByPrimaryKey(price);
	}

	@Override
	public void delRoomPrice(TCCPrice price) {
		priceMapper.deleteByPrimaryKey(price);
	}

}
