package com.yttx.yttxps.service;


import java.util.List;

import com.yttx.yttxps.model.Dict;
import com.yttx.yttxps.model.DictExample;


public interface IDictService {
	List<Dict> selectDict(DictExample example);
}