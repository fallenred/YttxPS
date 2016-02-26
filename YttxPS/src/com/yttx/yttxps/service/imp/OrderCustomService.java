package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TOrderCustomMapper;
import com.yttx.yttxps.model.TOrderCustomExample;
import com.yttx.yttxps.model.TOrderCustomWithBLOBs;
import com.yttx.yttxps.service.IOrderCustomService;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.xml.ResScheduleXMLConverter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Root;


@Service("orderCustomService")
public class OrderCustomService implements IOrderCustomService {

	@Autowired
	private IPubService<TOrderCustomWithBLOBs> pubService;
	
	@Autowired
	private TOrderCustomMapper<TOrderCustomWithBLOBs> orderCustomMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return orderCustomMapper.selectCountSelective(map);
	}

	@Override
	public List<TOrderCustomWithBLOBs> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, orderCustomMapper);
	}

	@Override
	public int insert(TOrderCustomWithBLOBs record) {
		return orderCustomMapper.insert(record);
	}

	@Override
	public int update(TOrderCustomWithBLOBs record) throws Exception {
		Body body = record.getBody();
		String fcResSnapshot = "";
		if (body != null) {
			Root root = new Root(record.getBody());
			fcResSnapshot = ResScheduleXMLConverter.toXml("http://www.yttx.com/", root);
		}
		record.setFcRessnapshot(fcResSnapshot);
		return orderCustomMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return orderCustomMapper.deleteByPrimaryKey(new BigDecimal(no));
	}

	@Override
	public List<TOrderCustomWithBLOBs> selectTOrderCustom(TOrderCustomExample example) {
		// TODO Auto-RouteArrangeerated method stub
		return orderCustomMapper.selectByExampleWithBLOBs(example);
	}
	
}
