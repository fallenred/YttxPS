package com.yttx.yttxps.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Room;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月19日
 * @email me@lonvoy.com
 *
 */
public interface IRoomService {
	
	@Transactional(readOnly = true)
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	List<Room> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	Room selectRoomInfo(BigDecimal index);
	
	@Transactional
	int insert(Room room);
	
	@Transactional
	int update(Room room);
	
	@Transactional
	int delete(BigDecimal index);
	
	@Transactional(readOnly = true)
	BigDecimal selectSequence();
	
}
