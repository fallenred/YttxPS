package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Accomadation;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 *
 */
public interface IAccomadationService {
	
	@Transactional(readOnly = true)
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	List<Accomadation> selectSelectivePage(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	Accomadation selectAccomadationInfo(String no);
	
	@Transactional
	int insert(Accomadation accomadation);
	
	@Transactional
	int update(Accomadation accomadation);
	
	@Transactional
	int delete(String no);
}
