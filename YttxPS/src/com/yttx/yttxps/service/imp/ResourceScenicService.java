package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TResourceScenicMapper;
import com.yttx.yttxps.model.TResourceScenic;
import com.yttx.yttxps.model.TResourceScenicExample;
import com.yttx.yttxps.service.IPubService;
import com.yttx.yttxps.service.IResourceScenicService;


@Service("resourceScenicService")
public class ResourceScenicService implements IResourceScenicService {

	@Autowired
	private IPubService<TResourceScenic> pubService;
	
	@Autowired
	private TResourceScenicMapper<TResourceScenic> resourceScenicMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return resourceScenicMapper.selectCountSelective(map);
	}

	@Override
	public List<TResourceScenic> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, resourceScenicMapper);
	}

	@Override
	public int insert(TResourceScenic record) {
		return resourceScenicMapper.insert(record);
	}

	@Override
	public int update(TResourceScenic record) {
		return resourceScenicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(BigDecimal index) {
		return resourceScenicMapper.deleteByPrimaryKey(index);
	}
	
	@Override
	public List<TResourceScenic> selectTResourceScenic(TResourceScenicExample example) {
		// TODO Auto-generated method stub
		return resourceScenicMapper.selectByExample(example);
	}

}
