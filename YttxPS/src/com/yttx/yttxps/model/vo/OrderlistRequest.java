package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.TOrderlistWithBLOBs;

public class OrderlistRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * modify by marongcai
	 * 订单查询新增条件需要，修改了使用的对象
	 * 2016-3-15
	 */
	private static final long serialVersionUID = 1L;

	private TOrderlistWithBLOBs orderlistWithBLOBs;

	public TOrderlistWithBLOBs getOrderlistWithBLOBs() {
		return orderlistWithBLOBs;
	}

	public void setOrderlistWithBLOBs(TOrderlistWithBLOBs orderlistWithBLOBs) {
		this.orderlistWithBLOBs = orderlistWithBLOBs;
	}

	public void copyOrderlist(Map<String, Object> map) {
		if (orderlistWithBLOBs != null) {
			map.put("fsNo", orderlistWithBLOBs.getFsNo() == null ? "" : orderlistWithBLOBs.getFsNo());
			map.put("fsName", orderlistWithBLOBs.getFsName() == null ? "" : orderlistWithBLOBs.getFsName());
			map.put("fsType", orderlistWithBLOBs.getFsType() == null ? "" : orderlistWithBLOBs.getFsType());
			map.put("fiStat", orderlistWithBLOBs.getFiStat() == null ? "" : orderlistWithBLOBs.getFiStat());
			map.put("fsOperId", orderlistWithBLOBs.getFsOperId() == null ? "" : orderlistWithBLOBs.getFsOperId());
			map.put("fsTaName", orderlistWithBLOBs.getFsTaName() == null ? "" : orderlistWithBLOBs.getFsTaName());
			map.put("fsId", orderlistWithBLOBs.getFsId() == null ? "" : orderlistWithBLOBs.getFsId());
		}
	}
}