package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Car;

/**
 * 
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 *
 */
public interface ICarService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Car> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Car car);
	
	@Transactional
	int update(Car car);
	
	@Transactional
	int delete(String no);
}
