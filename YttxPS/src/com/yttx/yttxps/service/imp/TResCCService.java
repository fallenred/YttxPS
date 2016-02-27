package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.TResCCMapper;
import com.yttx.yttxps.model.TResCCKey;
import com.yttx.yttxps.service.ITResCCService;

@Service
public class TResCCService implements ITResCCService {
	
	@Autowired
	private TResCCMapper resCCMapper;

	@Override
	public List<TResCCKey> findTResCCKey(Map<String, Object> map) {
		return resCCMapper.selectTResCC(map);
	}

}
