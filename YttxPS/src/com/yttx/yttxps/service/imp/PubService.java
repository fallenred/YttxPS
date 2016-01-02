package com.yttx.yttxps.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.RegionMapMapper;
import com.yttx.yttxps.mapper.SysDepMapper;
import com.yttx.yttxps.mapper.SysDepRightMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.SysOperRightMapper;
import com.yttx.yttxps.model.RegionMap;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.ISysService;


@Service("pubService")
public class PubService implements IPubService {
	
	@Autowired
	private RegionMapMapper regionMapMapper;

	@Override
	public List<RegionMap> findRegionByManageNo(String key) {
		// TODO Auto-generated method stub
		return regionMapMapper.selectByManageNo(key);
	}

}
