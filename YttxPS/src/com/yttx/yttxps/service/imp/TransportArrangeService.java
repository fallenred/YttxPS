package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int insert(TtransportArrange record) {
		return transportArrangeMapper.insert(record);
	}

	@Override
	public int update(TtransportArrange record) {
		return transportArrangeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(TtransportArrangeKey key) {
		return transportArrangeMapper.deleteByPrimaryKey(key);
	}
	
	@Override
	public List<TtransportArrange> selectTtransportArrange(TtransportArrangeExample example) {
		// TODO Auto-generated method stub
		return transportArrangeMapper.selectByExample(example);
	}
	
}
