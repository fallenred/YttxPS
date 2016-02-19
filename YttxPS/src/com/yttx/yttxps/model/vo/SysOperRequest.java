package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.model.SysOper;

/**
 * 类描述：操作员分页页面请求对象
 * @author sunchao
 * @date 2016年2月1日 下午4:23:43
 */
public class SysOperRequest extends JqGridRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private SysOper sysOper = new  SysOper();

	public SysOper getSysOper() {
		return sysOper;
	}

	public void setSysOper(SysOper sysOper) {
		this.sysOper = sysOper;
	}

	public void copySysOper(Map<String, Object> map) {
		if (sysOper != null) {
			//操作员ID
			map.put("sysOperId", StringUtil.nullOrBlank(sysOper.getSysOperId())?null:sysOper.getSysOperId());
			//操作员名称
			map.put("sysOperName", StringUtil.nullOrBlank(sysOper.getSysOperName())?null:sysOper.getSysOperName());
			//部门编号
			map.put("depNo",sysOper.getDepNo());
			//状态
			map.put("stat",sysOper.getStat());
		}
	}
}
