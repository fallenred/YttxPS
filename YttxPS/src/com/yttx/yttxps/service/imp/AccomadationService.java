package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.AccomadationMapper;
import com.yttx.yttxps.mapper.RoomMapper;
import com.yttx.yttxps.model.Accomadation;
import com.yttx.yttxps.model.Room;
import com.yttx.yttxps.model.RoomExample;
import com.yttx.yttxps.service.IAccomadationService;
import com.yttx.yttxps.service.IPubService;

/**
 * @author Lonvoy
 * @createDate 2016年1月18日
 * @email me@lonvoy.com
 * 
 */
@Service("accomadationService")
public class AccomadationService implements IAccomadationService {

	@Autowired
	private IPubService<Accomadation> pubService;
	
	@Autowired
	private AccomadationMapper<Accomadation> accomadationMapper;
	
	@Autowired
	private RoomMapper<Room> roomMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return accomadationMapper.selectCountSelective(map);
	}

	@Override
	public List<Accomadation> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, accomadationMapper);
	}
	
	@Override
	public List<Accomadation> selectSelective(Map<String, Object> map) {
		return accomadationMapper.selectSelective(map);
	}

	@Override
	public int insert(Accomadation record) {
		record.setNo(String.format("bg%08d", accomadationMapper.selectSequence().longValue()));
		return accomadationMapper.insert(record);
	}

	@Override
	public int update(Accomadation record) {
		//当作废酒店时，需要把属于该酒店的所有房型作废
		if(!BigDecimal.ONE.equals(record.getStat())) {
			Room room = new Room();
			room.setFiStat(BigDecimal.valueOf(2));
			RoomExample roomExample = new RoomExample();
			com.yttx.yttxps.model.RoomExample.Criteria roomCriteria = roomExample.createCriteria();
			roomCriteria.andFsAccomnoEqualTo(record.getNo());
			roomMapper.updateByExampleSelective(room, roomExample);
		}
		
		return accomadationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return accomadationMapper.deleteByPrimaryKey(no);
	}

	@Override
	public Accomadation selectAccomadationInfo(String no) {
		return accomadationMapper.selectByPrimaryKey(no);
	}





}
