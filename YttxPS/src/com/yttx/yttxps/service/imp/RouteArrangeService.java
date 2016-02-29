package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.mapper.TRouteArrangeMapper;
import com.yttx.yttxps.mapper.TRouteCCMapper;
import com.yttx.yttxps.model.RouteCCType;
import com.yttx.yttxps.model.TRouteArrange;
import com.yttx.yttxps.model.TRouteArrangeExample;
import com.yttx.yttxps.model.TRouteArrangeWithBLOBs;
import com.yttx.yttxps.model.TRouteCCExample;
import com.yttx.yttxps.model.TRouteCCExample.Criteria;
import com.yttx.yttxps.model.TRouteCCKey;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IRouteArrangeService;


@Service("routeArrangeService")
public class RouteArrangeService implements IRouteArrangeService {

	@Autowired
	private IPubService<TRouteArrange> pubService;
	
	@Autowired
	private TRouteArrangeMapper<TRouteArrange> routeArrangeMapper;
	
	@Autowired
	private TRouteCCMapper<TRouteCCKey> routeCCMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return routeArrangeMapper.selectCountSelective(map);
	}

	@Override
	public List<TRouteArrange> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, routeArrangeMapper);
	}

	@Override
	public int insert(TRouteArrangeWithBLOBs record) {
		record.setFsId(String.format("%010d", routeArrangeMapper.selectFsId()));
		return routeArrangeMapper.insert(record);
	}
	
	@Override
	public void insertRouteCC(TRouteArrangeWithBLOBs record) {
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCCMapper.insert(routeCC);
		}
	}

	@Override
	public int update(TRouteArrangeWithBLOBs record) {
		return routeArrangeMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void updateRouteCC(TRouteArrangeWithBLOBs record) {
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFiDayflagEqualTo(record.getFiDayflag());
		criteria.andFsRoutenoEqualTo(record.getFsId());
		routeCCMapper.deleteByExample(example);
		for(TRouteCCKey routeCC : record.getRoutecc()) {
			routeCCMapper.insert(routeCC);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(String no) {
		routeArrangeMapper.deleteByPrimaryKey(no);
		//删除线路产品-资源
		TRouteCCExample example = new TRouteCCExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsRoutenoEqualTo(no);
		routeCCMapper.deleteByExample(example);
	}

	@Override
	public List<TRouteArrange> selectTRouteArrange(TRouteArrangeExample example) {
		return routeArrangeMapper.selectByExample(example);
	}
	
	public List<RouteCCType> findRouteCCType(Map<String, Object> map) {
		return routeArrangeMapper.selectRouteCCType(map);
	}
	
	public List<TRouteCCKey> findTRouteCCKey(TRouteCCExample example) {
		return routeCCMapper.selectByExample(example);
	}
}
