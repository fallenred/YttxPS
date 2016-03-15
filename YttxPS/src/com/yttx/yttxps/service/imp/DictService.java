package com.yttx.yttxps.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.DictMapper;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictExample.Criteria;
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
	public List<Dict> selectAllDict() {
		DictExample example = new DictExample();
		Criteria criteria = example.createCriteria();
		criteria.andFsParentnoIsNotNull();
		List<Dict> dicts = dictMapper.selectByExample(example);//加载所有parantNo不为空的字典数据
		return dicts;
	}
}
