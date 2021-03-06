package com.yttx.yttxps.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yttx.yttxps.model.Scenic;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;


public interface IScenicService {
	
	@Transactional(readOnly = true) 
	public int selectCountSelective(Map<String, Object> map);
	
	@Transactional(readOnly = true) 
	List<Scenic> selectSelectivePage(Map<String, Object> map);
	
	@Transactional
	int insert(Scenic scenic);
	
	@Transactional
	int update(Scenic scenic);
	
	@Transactional
	int delete(String no);
}
