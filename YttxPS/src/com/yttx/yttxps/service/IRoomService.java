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
	Room selectRoomInfo(String fsRoomno);
	
	@Transactional
	int insert(Room room);
	
	@Transactional
	int update(Room room);
	
	@Transactional
	int delete(String fsRoomno);
	
	@Transactional(readOnly = true)
	BigDecimal selectSequence();

	/**
     * 查询酒店房型（线路配置时使用）
     * add by huangtao
     * 2016-02-14
     * @param map
     * @return
     */
	List<Room> selectSelective(Map<String, Object> map);
	
}
