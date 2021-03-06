package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TResourceScenicMapper;
import com.yttx.yttxps.mapper.TticketMapper;
import com.yttx.yttxps.mapper.TticketPriceMapper;
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
	private TticketPriceMapper<Tticket> ticketPriceMapper;

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
	
	/**
	 * 查询门票价格总数
	 */
	@Override
	public int selectCountTicketPrice(Map<String, Object> map) {
		return ticketPriceMapper.selectCountSelective(map);
	}

	/**
	 * 分页查询票价
	 */
	@Override
	public List<Tticket> selectTicketPricePage(Map<String, Object> map) {
		return pubService.doPage(map, ticketPriceMapper);
	}
	
	/**
	 * 新增门票资源信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(Tticket record) {
		record.setFsNo("mp"+String.format("%08d", ticketMapper.selectFsNo()));
		ticketMapper.insert(record);
		//新增景区资源对照数据
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFiIndex(BigDecimal.valueOf(resourceScenicMapper.getSeq()));
		resourceScenic.setFsResno(record.getFsNo());
		resourceScenic.setFsRestype("mp");
		resourceScenic.setFsScenicno(record.getFsScenicno());
		resourceScenicMapper.insert(resourceScenic);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(Tticket record) {
		ticketMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String no) {
		ticketMapper.deleteByPrimaryKey(no);
		TCCPriceExample example = new TCCPriceExample();
		Criteria criteriac = example.createCriteria();
		criteriac.andFsResnoEqualTo(no);
		tccPriceMapper.deleteByExample(example);
	}

	@Override
	public List<Tticket> selectTticket(TticketExample example) {
		return ticketMapper.selectByExample(example);
	}

	@Override
	public void insertTicketPrice(Tticket record) {
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("mp");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertPrice(price);
		}
	}

	@Override
	public void updateTicketPrice(Tticket record) {
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			price.setFtStartdate(record.getFtStartdate());
			price.setFtEnddate(record.getFtEnddate());
			price.setFsRestype("mp");
			price.setFsResno(record.getFsNo());
			tccPriceMapper.insertPrice(price);
		}
	}

	@Override
	public void deleteTicketPrice(TCCPrice price) {
		TCCPriceExample example = new TCCPriceExample();
		Criteria criteria = example.createCriteria();
		criteria.andFtStartdateEqualTo(price.getFtStartdate());
		criteria.andFtEnddateEqualTo(price.getFtEnddate());
		criteria.andFsRestypeEqualTo("mp");
		criteria.andFsResnoEqualTo(price.getFsResno());
		tccPriceMapper.deleteByExample(example);
	}

}
