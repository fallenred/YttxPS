package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Driver;

/**
 * 驾驶员信息和视图映射
 * @author Lonvoy
 * @createDate 2016年1月12日
 * @email me@lonvoy.com
 * 
 */
public class DriverRequest extends JqGridRequest implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	private Driver driver;

	public void copyDriver(Map<String, Object> map) {
		if (driver != null) {
			map.put("index", driver.getIndex() == null ? "" : driver.getIndex());
			map.put("name", driver.getName() == null ? "" : driver.getName());
			map.put("gender",driver.getGender() == null ? "" : driver.getGender());
			map.put("birth", driver.getBirth() == null? "" : driver.getBirth());
			map.put("stat", driver.getStat() == null ? "" : driver.getStat());
		}
	}
}