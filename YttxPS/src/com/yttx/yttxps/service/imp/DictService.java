package com.yttx.yttxps.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.DictMapper;
import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.service.IDictService;


@Service("dictService")
public class DictService implements IDictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<Dict> selectDict(DictExample example) {
		return dictMapper.selectByExample(example);
	}
}
