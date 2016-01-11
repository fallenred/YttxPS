package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TtransportMapper;
import com.yttx.yttxps.model.Ttransport;
import com.yttx.yttxps.model.TtransportExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ITransportService;


@Service("transportService")
public class TransportService implements ITransportService {

	@Autowired
	private IPubService<Ttransport> pubService;
	
	@Autowired
	private TtransportMapper<Ttransport> transportMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return transportMapper.selectCountSelective(map);
	}

	@Override
	public List<Ttransport> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, transportMapper);
	}

	@Override
	public int insert(Ttransport record) {
		return transportMapper.insert(record);
	}

	@Override
	public int update(Ttransport record) {
		return transportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return transportMapper.deleteByPrimaryKey(no);
	}

	@Override
	public List<Ttransport> selectTtransport(TtransportExample example) {
		// TODO Auto-generated method stub
		return transportMapper.selectByExample(example);
	}

}
