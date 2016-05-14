package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;
import com.yttx.yttxps.model.DictKey;


public interface IDictService {
	List<Dict> selectDict(DictExample example);
	
	Map<String, Object> selectDictMap(DictExample example);

	Map<String, Object> selectDictMapName4key(DictExample example);

	List<DictKey> selectDictByParentNo(String parentNo);

	Dict selectDict(DictKey key);
}

