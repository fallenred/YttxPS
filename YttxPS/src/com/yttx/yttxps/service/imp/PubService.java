package com.yttx.yttxps.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.comm.DateUtil;
import com.yttx.yttxps.mapper.IBaseMapper;
import com.yttx.yttxps.mapper.RegionMapMapper;
import com.yttx.yttxps.mapper.ScenicMapper;
import com.yttx.yttxps.mapper.TCCPriceMapper;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.ResoucePrice;
import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.service.IPubService;


@Service("pubService")
public class PubService<T> implements IPubService<T> {
	
	@Autowired
	private RegionMapMapper regionMapMapper;
	
	@Autowired
	private TCCPriceMapper tCCPriceMapper;
	
	@Autowired  
	private ScenicMapper<Scenic> scenicMapper;

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
			total =(records%rows==0)? (records/rows):(records/rows+ 1);
			map.put("total", total);
		}
		if(page > total) {
			map.put("page", total);
		}
		map.put("startrow", rows*(page - 1));
		map.put("endrow", rows*page);
		return mapper.selectSelectivePage(map);
	}


	@Override
	public String findRegionFullName(String no) {
		RegionMap r1 = regionMapMapper.selectByPrimaryKey(no);
		if (r1 == null) return "";
		if(r1.getLvl().intValue() == 1) {
			return r1.getName();
		} else {
			return findRegionFullName(r1.getManageno()) + "-" + r1.getName();
		}
	}


	@Override
	public List<ResoucePrice> findResoucePrice(ResoucePrice resQue){
		List<ResoucePrice> rps = null;
		List<String> days = DateUtil.getPerDayOfPeriodTime(resQue.getStartDate(), resQue.getEndDate());
		if(days!=null&&days.size()>0){
			rps=new ArrayList<ResoucePrice>();
			for(String day:days){
				resQue.setDate(day);
				List<ResoucePrice> subRps = tCCPriceMapper.selectOneDaySelective(resQue);
				if(subRps != null)
					rps.addAll(subRps);
			}
		}
		return rps;
	}


	@Override
	public List<Scenic> findAllScennic() {
		return scenicMapper.findAll();
	}
}
