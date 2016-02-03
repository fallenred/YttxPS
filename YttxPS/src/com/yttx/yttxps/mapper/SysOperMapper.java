package com.yttx.yttxps.mapper;

import java.util.Map;

import com.yttx.yttxps.model.SysOper;

public interface SysOperMapper extends IBaseMapper<SysOper>{
	
	public SysOper findById(String  sysOperId);
	
	public int  insert(SysOper sysOper);
	
	public int  update(SysOper sysOper);

	public void updateOperStatByDepNo(SysOper oper);
    
    /**
     * 更新用户信息
     */
    public boolean updateOperByOperId(Map<String, Object> map);

    /**
     * 删除一个用户
     */
	public void deleteOperByOperId(String sysOperId);
}
