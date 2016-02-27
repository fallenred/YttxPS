package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.TResCCKey;

public interface ITResCCService {

	@Transactional(readOnly = true)
	List<TResCCKey> findTResCCKey(Map<String, Object> map);
}
