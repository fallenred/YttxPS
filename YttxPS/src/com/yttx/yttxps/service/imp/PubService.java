package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.IBaseMapper;
import com.yttx.yttxps.mapper.RegionMapMapper;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.service.IPubService;


@Service("pubService")
public class PubService<T> implements IPubService<T> {
	
	@Autowired
	private RegionMapMapper regionMapMapper;

	@Override
	public List<RegionMap> findRegionByManageNo(String key) {
		return regionMapMapper.selectByManageNo(key);
	}


	@Override
	public List<T> doPage(Map<String, Object> map, IBaseMapper<T> mapper) {
		//	查询总记录条数
		int records = mapper.selectCountSelective(map);
		map.put("records", records );
		int total = 0;
		int rows = (Integer) map.get("rows");
		int page = (Integer)map.get("page");
		if(records  > 0) {
			total = records/rows + 1;
			map.put("total", total);
		}
		if(page > total) {
			map.put("page", total);
		}
		map.put("startrow", rows*(page - 1));
		map.put("endrow", rows*page);
		return mapper.selectSelectivePage(map);
	}





}
