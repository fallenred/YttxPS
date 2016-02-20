package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TOrderlistMapper;
import com.yttx.yttxps.model.TOrderlistExample;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;
import com.yttx.yttxps.service.IOrderlistService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.Body;
import com.yttx.yttxps.xml.CommResSnapshotXMLConverter;


@Service("orderlistService")
public class OrderlistService implements IOrderlistService {

	@Autowired
	private IPubService<TOrderlistWithBLOBs> pubService;
	
	@Autowired
	private TOrderlistMapper<TOrderlistWithBLOBs> orderlistMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return orderlistMapper.selectCountSelective(map);
	}

	@Override
	public List<TOrderlistWithBLOBs> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, orderlistMapper);
	}

	@Override
	public int insert(TOrderlistWithBLOBs record) {
		return orderlistMapper.insert(record);
	}

	@Override
	public int update(TOrderlistWithBLOBs record) {
		Body body = new Body();
		body.setReslist(record.getReslist());
		String fcCommressnapshot = CommResSnapshotXMLConverter.convert2XML(body);
		record.setFcCommressnapshot(fcCommressnapshot);
		return orderlistMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return orderlistMapper.deleteByPrimaryKey(no);
	}

	@Override
	public List<TOrderlistWithBLOBs> selectTOrderlist(TOrderlistExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return orderlistMapper.selectByExampleWithBLOBs(example);
	}
	
}
