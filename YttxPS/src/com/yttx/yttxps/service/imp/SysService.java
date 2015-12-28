package com.yttx.yttxps.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.SysDepMapper;
import com.yttx.yttxps.mapper.SysDepRightMapper;
import com.yttx.yttxps.mapper.SysOperMapper;
import com.yttx.yttxps.mapper.SysOperRightMapper;
import com.yttx.yttxps.model.SysDep;
import com.yttx.yttxps.model.SysDepRight;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.model.SysOperRight;

import com.yttx.yttxps.service.ISysService;


@Service("sysService")
public class SysService implements ISysService {
	
	@Autowired
	private SysOperMapper sysOperMapper;
	
	@Autowired
	private SysOperRightMapper sysOperRightMapper;
	
	@Autowired
	private SysDepMapper sysDepMapper;
	
	
	@Autowired
	private SysDepRightMapper sysDepRightMapper;

	@Override
	public SysOper findOperById(String sysOperId) {
		return sysOperMapper.findById(sysOperId);
	}
	
	@Override
	public List<SysOperRight> findOperRight(String sysOperId ){
		return sysOperRightMapper.findById(sysOperId);
	}

	@Override
	public SysDep findDepByNo(long depNo) {
		return sysDepMapper.findByNo(depNo);
	}

	@Override
	public List<SysDepRight> findDepRight(long depNo) {
		return sysDepRightMapper.findByNo(depNo);
	}

	@Override
	public List<SysDep> findDepAll() {
		return sysDepMapper.findAll();
	}

	@Override
	public int updateSysOper(SysOper sysOper) {
		return sysOperMapper.update(sysOper);
	}

}
