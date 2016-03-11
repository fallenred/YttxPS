package com.yttx.yttxps.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yttx.yttxps.mapper.CarMapper;
import com.yttx.yttxps.model.Car;
import com.yttx.yttxps.service.ICarService;
import com.yttx.yttxps.service.IPubService;

/**
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 * 
 */
@Service("carService")
public class CarService implements ICarService {

	@Autowired
	private IPubService<Car> pubService;
	
	@Autowired
	private CarMapper<Car> carMapper;

	@Override
	public int selectCountSelective(Map<String, Object> map) {
		return carMapper.selectCountSelective(map);
	}

	@Override
	public List<Car> selectSelectivePage(Map<String, Object> map) {
		return pubService.doPage(map, carMapper);
	}

	@Override
	public int insert(Car record) {
		record.setSeqNum("cl"+selectSequence());
		return carMapper.insert(record);
	}

	@Override
	public int update(Car record) {
		return carMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String no) {
		return carMapper.deleteByPrimaryKey(no);
	}

	public String selectSequence(){
		 return String.format("%08d", carMapper.selectNo());
	}



}
