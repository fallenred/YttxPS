package com.yttx.yttxps.service.imp;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.mapper.TtransportArrangeMapper;
import com.yttx.yttxps.model.TCCPrice;
import com.yttx.yttxps.model.TCCPriceExample;
import com.yttx.yttxps.model.TCCPriceExample.Criteria;
import com.yttx.yttxps.model.TtransportArrange;
import com.yttx.yttxps.model.TtransportArrangeExample;
import com.yttx.yttxps.model.TtransportArrangeKey;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ITransportArrangeService;


@Service("transportArrangeService")
public class TransportArrangeService implements ITransportArrangeService {

	@Autowired
	private IPubService<TtransportArrange> pubService;
	
	@Autowired
	private TtransportArrangeMapper<TtransportArrange> transportArrangeMapper;
	
	@Autowired
	private TCCPriceMapper priceMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return transportArrangeMapper.selectCountSelective(map);
	}

	@Override
	public List<TtransportArrange> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, transportArrangeMapper);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void insert(TtransportArrange record) throws ParseException {
		transportArrangeMapper.insert(record);
		TCCPrice price = new TCCPrice();
		price.setFtStartdate(record.getFtStartdate());
		if (record.getFtEnddate() == null) {
			price.setFtEnddate(record.getFtStartdate());
		} else {
			price.setFtEnddate(record.getFtEnddate());
		}
		price.setFsRestype("cx");
		price.setFsResno(record.getFsNo());
		price.setFsCcno("000000");
		price.setFdPrice(record.getFdPrice());
		priceMapper.insert(price);
		
	}

	@Override
	public void update(TtransportArrange record) throws ParseException {
		TtransportArrangeExample example = new TtransportArrangeExample();
		TtransportArrangeExample.Criteria criteria = example.createCriteria();
		criteria.andFsNoEqualTo(record.getFsNo());
		transportArrangeMapper.updateByExample(record, example);
		TCCPrice price = new TCCPrice();
		price.setFsRestype("cx");
		price.setFsCcno("000000");
		price.setFdPrice(record.getFdPrice());
		price.setFtStartdate(record.getFtStartdate());
		price.setFsResno(record.getFsNo());
		if (record.getFtEnddate() == null) {
			price.setFtEnddate(record.getFtStartdate());
		} else {
			price.setFtEnddate(record.getFtEnddate());
		}
		priceMapper.updateByPrimaryKey(price);
	}

	@Override
	public int deleteByNo(String no) {
		TCCPriceExample priceExample = new TCCPriceExample();
		Criteria criteria1 = priceExample.createCriteria();
		criteria1.andFsResnoEqualTo(no);
		criteria1.andFsRestypeEqualTo("cx");
		criteria1.andFsCcnoEqualTo("000000");
		priceMapper.deleteByExample(priceExample);
		TtransportArrangeExample example = new TtransportArrangeExample();
		TtransportArrangeExample.Criteria criteria= example.createCriteria();
		criteria.andFsNoEqualTo(no);
		return transportArrangeMapper.deleteByExample(example);
		
	}
	
	@Override
	public List<TtransportArrangeKey> selectTtransportArrange(TtransportArrangeExample example) {
		return transportArrangeMapper.selectByExample(example);
	}
	
	@Override
	public List<TtransportArrange> selectTtransportArrangeView(TtransportArrangeExample example) {
		return transportArrangeMapper.selectTtransportArrangeByExample(example);
	}
	
	@Override
	public int selectFsNo() {
		return transportArrangeMapper.selectFsNo();
	}
	
}
