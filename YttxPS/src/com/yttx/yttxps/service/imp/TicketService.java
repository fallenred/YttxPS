package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TResourceScenicMapper;
import com.yttx.yttxps.mapper.TticketMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceExample.Criteria;
import com.yttx.yttxps.model.TResourceScenic;
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

	@Autowired
	private TResourceScenicMapper<TResourceScenic> resourceScenicMapper;
	
	@Autowired
	private TCCPriceMapper tccPriceMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return ticketMapper.selectCountSelective(map);
	}

	@Override
	public List<Tticket> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, ticketMapper);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(Tticket record) {
		record.setFsNo(String.format("%010d", ticketMapper.selectFsNo()));
		ticketMapper.insert(record);
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFiIndex(BigDecimal.valueOf(resourceScenicMapper.getSeq()));
		resourceScenic.setFsResno(record.getFsNo());
		resourceScenic.setFsRestype("mp");
		resourceScenic.setFsScenicno(record.getFsScenicno());
		resourceScenicMapper.insert(resourceScenic);
		//插入资源消费选项定价表
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			price.setFsRestype("mp");
			price.setFtDate(new Date());
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertSelective(price);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(Tticket record) {
		ticketMapper.updateByPrimaryKeySelective(record);
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			TCCPriceExample example = new TCCPriceExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsRestypeEqualTo("mp");
			criteria.andFsResnoEqualTo(record.getFsNo());
			criteria.andFsCcnoEqualTo(price.getFsCcno());
			tccPriceMapper.updateByExampleSelective(price, example);
		}
	}

	@Override
	public int delete(String no) {
		return ticketMapper.deleteByPrimaryKey(no);
	}

	@Override
	public List<Tticket> selectTticket(TticketExample example) {
		// TODO Auto-generated method stub
		return ticketMapper.selectByExample(example);
	}

}
