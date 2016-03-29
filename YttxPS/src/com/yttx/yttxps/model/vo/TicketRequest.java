package com.yttx.yttxps.model.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.yttx.yttxps.model.Tticket;
import com.yttx.yttxps.model.TticketExample;
import com.yttx.yttxps.model.TticketExample.Criteria;

public class TicketRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tticket getTicket() {
		return ticket;
	}

	public void setTicket(Tticket ticket) {
		this.ticket = ticket;
	}

	private Tticket ticket;

	public void copyTicket(Map<String, Object> map) {
		if (ticket != null) {
			map.put("fsNo", ticket.getFsNo() == null ? "" : ticket.getFsNo());
			map.put("fsName", ticket.getFsName() == null ? "" : ticket.getFsName());
			/**
			 * modify by marongcai
			 * 修改景区编号为景区名称
			 * 2016-3-16
			 * modify by start
			 */
			map.put("fsScenicname", ticket.getFsScenicname() == null ? "" : ticket.getFsScenicname());
			/**
			 * modify end
			 */
			map.put("fsType", ticket.getFsType() == null ? "" : ticket.getFsType());
			map.put("fiStat", ticket.getFiStat() == null ? "" : ticket.getFiStat());
		}
	}
	
	public void copyTicket(Criteria criteria) {
		if (ticket != null) {
			if (ticket.getFsScenicno() != null)
				criteria.andFsScenicnoEqualTo(ticket.getFsScenicno());
			if (ticket.getFiStat() != null)
				criteria.andFiStatEqualTo(new BigDecimal(ticket.getFiStat()));
		}
	}
}