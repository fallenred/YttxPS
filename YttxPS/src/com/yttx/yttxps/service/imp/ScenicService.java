package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.ScenicMapper;
import com.yttx.yttxps.mapper.TticketMapper;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IScenicService;


@Service("scenicService")
public class ScenicService implements IScenicService {

	@Autowired
	private IPubService<Scenic> pubService;
	
	@Autowired
	private ScenicMapper<Scenic> scenicMapper;
	
	@Autowired
	TticketMapper<Tticket> ticketMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return scenicMapper.selectCountSelective(map);
	}

	@Override
	public List<Scenic> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, scenicMapper);
	}

	@Override
	public int insert(Scenic record) {
		return scenicMapper.insert(record);
	}

	@Override
	public int update(Scenic record) {
		//如果是作废景区，则需要把景区门票一同作废
		if(!BigDecimal.ONE.equals(record.getStat())){
			Tticket ticket = new Tticket();
			ticket.setFiStat(Integer.valueOf(2));   //无效
			TticketExample ticketExample = new TticketExample();
			com.yttx.yttxps.model.TticketExample.Criteria ticketCriteria = ticketExample.createCriteria();
			ticketCriteria.andFsScenicnoEqualTo(record.getNo());
			ticketMapper.updateByExampleSelective(ticket, ticketExample);
		}
		
		return scenicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return scenicMapper.deleteByPrimaryKey(no);
	}





}
