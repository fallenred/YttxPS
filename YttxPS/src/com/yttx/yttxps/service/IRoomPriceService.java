package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.RoomPrice;
import com.yttx.yttxps.model.TCCPrice;

public interface IRoomPriceService {

	@Transactional(readOnly = true)
	List<RoomPrice> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	void insertRoomPrice(TCCPrice price);
	
	@Transactional
	void updateRoomPrice(TCCPrice price);
	
	@Transactional
	void delRoomPrice(TCCPrice price);
}
