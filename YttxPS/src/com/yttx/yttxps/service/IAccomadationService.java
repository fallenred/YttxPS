package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Accomadation;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月18日
 * @email me@lonvoy.com
 *
 */
public interface IAccomadationService {
	
	@Transactional(readOnly = true)
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	List<Accomadation> selectSelectivePage(Map<String, Object> map);
	
	/**
	 * 查询宾馆列表（线路配置时使用）
	 * add by huangtao
	 * 2016-02-14
	 * @param map
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Accomadation> selectSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true)
	Accomadation selectAccomadationInfo(String no);
	
	@Transactional
	int insert(Accomadation accomadation);
	
	@Transactional
	int update(Accomadation accomadation);
	
	@Transactional
	int delete(String no);

	public List<Accomadation> selectSelectiveRgUnnecessary(
			Map<String, Object> map);
	
}
