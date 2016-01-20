package com.yttx.yttxps.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.DriverMapper;
import com.yttx.yttxps.model.Driver;
import com.yttx.yttxps.service.IDriverService;
import com.yttx.yttxps.service.IPubService;

/**
 * @author Lonvoy
 * @createDate 2016年1月12日
 * @email me@lonvoy.com
 * 
 */
@Service("driverService")
public class DriverService implements IDriverService {

	@Autowired
	private IPubService<Driver> pubService;
	
	@Autowired
	private DriverMapper<Driver> driverMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return driverMapper.selectCountSelective(map);
	}

	@Override
	public List<Driver> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, driverMapper);
	}

	@Override
	public int insert(Driver record) {
		return driverMapper.insert(record);
	}

	@Override
	public int update(Driver record) {
		return driverMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(BigDecimal no) {
		return driverMapper.deleteByPrimaryKey(no);
	}

	@Override
	public Driver selectDriverInfo(BigDecimal index) {
		// TODO Auto-generated method stub
		return driverMapper.selectByPrimaryKey(index);
	}

	@Override
	public BigDecimal selectSequence() {
		return driverMapper.selectSequence();
	}





}
