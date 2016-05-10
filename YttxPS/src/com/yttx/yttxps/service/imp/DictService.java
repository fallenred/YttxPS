package com.yttx.yttxps.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.DictMapper;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictKey;
import com.yttx.yttxps.service.IDictService;


@Service("dictService")
public class DictService implements IDictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<Dict> selectDict(DictExample example) {
		return dictMapper.selectByExample(example);
	}
	
	@Override
	public Map<String, Object> selectDictMap(DictExample example) {
		List<Dict> list = selectDict(example);
		Map<String, Object> map = new HashMap<String, Object>();
		for(Dict dict : list) {
			map.put(dict.getFsDictno(), dict.getFsDictname());
		}
		return map;
	}
	
	@Override
	public Map<String, Object> selectDictMapName4key(DictExample example) {
		List<Dict> list = selectDict(example);
		Map<String, Object> map = new HashMap<String, Object>();
		for(Dict dict : list) {
			map.put(dict.getFsDictname(), dict.getFsDictno());
		}
		return map;
	}
	
	@Override
	public List<DictKey> selectDictByParentNo(String parentNo) {
		return dictMapper.selectByParentNo(parentNo);
	}
}