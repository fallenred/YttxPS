package com.yttx.yttxps.mapper;

import java.util.List;
import java.util.Map;
import com.yttx.yttxps.model.SysDep;

public interface SysDepMapper<T> extends IBaseMapper<T> {
	
	public List<SysDep> findAll();
	
	public SysDep findByNo(long  depno);
	
	public SysDep findByDepName(String depName);
	
	public void  insert(SysDep sysDep);
	
	public void update(SysDep sysDep);
	
	/**
     * 
     * @param record
     * @return
     */
    int selectCountSelective(Map<String, Object> map);
    
    /**
     * @param map
     * @return List<Object>
     */
    List<T> selectSelectivePage(Map<String, Object> map);	
}
