package com.yttx.yttxps.model.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.yttx.yttxps.model.Tticket;

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
			map.put("fsScenicno", ticket.getFsScenicno() == null ? "" : ticket.getFsScenicno());
			map.put("fsType", ticket.getFsType() == null ? "" : ticket.getFsType());
			map.put("fiStat", ticket.getFiStat() == null ? "" : ticket.getFiStat());
		}
	}
}