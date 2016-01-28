package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.Calendar;
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
		//新增景区资源对照数据
		TResourceScenic resourceScenic = new TResourceScenic();
		resourceScenic.setFiIndex(BigDecimal.valueOf(resourceScenicMapper.getSeq()));
		resourceScenic.setFsResno(record.getFsNo());
		resourceScenic.setFsRestype("mp");
		resourceScenic.setFsScenicno(record.getFsScenicno());
		resourceScenicMapper.insert(resourceScenic);
		//插入资源消费选项定价表
		if (CollectionUtils.isEmpty(record.getTccPrices())) return;
		for (TCCPrice price : record.getTccPrices()) {
			Calendar calendar = Calendar.getInstance();
			//淡季处理
			if (record.getTccPrices().indexOf(price) < 8) {
				price.setFtStartdate(record.getFtStartdate());
				price.setFtEnddate(record.getFtEnddate());
			} else {
				//旺季结束日期  = 淡季开始日期 -1
				calendar.setTime(record.getFtStartdate());
				calendar.add(Calendar.YEAR, 1);
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				price.setFtEnddate(calendar.getTime());
				//旺季开始日期 = 淡季结束日期 +1
				calendar.setTime(record.getFtEnddate());
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				price.setFtStartdate(calendar.getTime());
			}
			price.setFsRestype("mp");
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
			Calendar calendar = Calendar.getInstance();
			//淡季处理
			if (record.getTccPrices().indexOf(price) < 8) {
				price.setFtStartdate(record.getFtStartdate());
				price.setFtEnddate(record.getFtEnddate());
			} else {
				//旺季结束日期  = 淡季开始日期 -1
				calendar.setTime(record.getFtStartdate());
				calendar.add(Calendar.YEAR, 1);
				calendar.add(Calendar.DAY_OF_YEAR, -1);
				price.setFtEnddate(calendar.getTime());
				//旺季开始日期 = 淡季结束日期 +1
				calendar.setTime(record.getFtEnddate());
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				price.setFtStartdate(calendar.getTime());
			}
			TCCPriceExample example = new TCCPriceExample();
			Criteria criteria = example.createCriteria();
			criteria.andFsRestypeEqualTo("mp");
			criteria.andFsResnoEqualTo(record.getFsNo());
			criteria.andFsCcnoEqualTo(price.getFsCcno());
			tccPriceMapper.updateByExampleSelective(price, example);
		}
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
		// TODO Auto-generated method stub
		return ticketMapper.selectByExample(example);
	}

}
