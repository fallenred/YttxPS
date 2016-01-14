package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Car;

/**
 * 车辆信息和视图映射
 * @author Lonvoy
 * @createDate 2016年1月14日
 * @email me@lonvoy.com
 * 
 */
public class CarRequest extends JqGridRequest implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	private Car car;

	public void copyCar(Map<String, Object> map) {
		if (car != null) {
			map.put("no",  car.getNo());
			map.put("brand",  car.getBrand());
			map.put("load", car.getLoad());
			map.put("regdate", car.getRegdate());
			map.put("company",  car.getCompany());
			map.put("tel", car.getTel());
			map.put("driverindex", car.getDriverindex());
			map.put("stat", car.getStat());
		}
	}
}