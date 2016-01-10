package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yttx.yttxps.mapper.TticketMapper;
import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ITicketService;


@Service("ticketService")
public class TicketService implements ITicketService {

	@Autowired
	private IPubService<Tticket> pubService;
	
	@Autowired
	private TticketMapper<Tticket> ticketMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return ticketMapper.selectCountSelective(map);
	}

	@Override
	public List<Tticket> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, ticketMapper);
	}

	@Override
	public int insert(Tticket record) {
		return ticketMapper.insert(record);
	}

	@Override
	public int update(Tticket record) {
		return ticketMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return ticketMapper.deleteByPrimaryKey(no);
	}
	
	@Override
	public int selectMaxSeq(TticketExample example) {
		return ticketMapper.selectMaxSeqByExample(example);
	}

	@Override
	public List<Tticket> selectTticket(TticketExample example) {
		// TODO Auto-generated method stub
		return ticketMapper.selectByExample(example);
	}

	@Override
	public int selectFsNo() {
		return ticketMapper.selectFsNo();
	}
	
}
