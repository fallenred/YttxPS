package com.yttx.yttxps.service.imp;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.TtransportArrangeMapper;
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
	}

	@Override
	public void update(TtransportArrange record) throws ParseException {
		TtransportArrangeExample example = new TtransportArrangeExample();
		TtransportArrangeExample.Criteria criteria = example.createCriteria();
		criteria.andFsNoEqualTo(record.getFsNo());
		transportArrangeMapper.updateByExample(record, example);
	}

	@Override
	public void deleteByNo(String no) {
		TtransportArrangeExample example = new TtransportArrangeExample();
		TtransportArrangeExample.Criteria criteria = example.createCriteria();
		criteria.andFsNoEqualTo(no);
		transportArrangeMapper.deleteByExample(example);
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
	public TtransportArrangeKey findUniqTtransportArrange(String fsNo) {
		return transportArrangeMapper.selectByPrimaryKey(fsNo);
	}
	
	@Override
	public int selectFsNo() {
		return transportArrangeMapper.selectFsNo();
	}
	
}
