package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.CustChgAu;

public class CustChgAuRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private CustChgAu custchgau;

	public CustChgAu getCustchgau() {
		return custchgau;
	}

	public void setCustchgau(CustChgAu custchgau) {
		this.custchgau = custchgau;
	}

	public void copyCustChgAu(Map<String, Object> map) {
		if (custchgau != null) {
			map.put("auditno", custchgau.getAuditno() == null ? "" : custchgau.getAuditno());
			if(custchgau.getAudittype() != null && custchgau.getAudittype().intValue() != 0 )
				map.put("audittype", custchgau.getAudittype());
			map.put("id", custchgau.getId() == null ? "" : custchgau.getId());
			map.put("name", custchgau.getName() == null ? "" : custchgau.getName());
			map.put("taname", custchgau.getTaname() == null ? "" : custchgau.getTaname());
			map.put("licenceno", custchgau.getLicenceno() == null ? "" : custchgau.getLicenceno());
			map.put("taxlicence", custchgau.getTaxlicence() == null ? "" : custchgau.getTaxlicence());
			map.put("contact", custchgau.getContact() == null ? "" : custchgau.getContact());
			map.put("certid", custchgau.getCertid() == null ? "" : custchgau.getCertid());
			map.put("email", custchgau.getEmail() == null ? "" : custchgau.getEmail());
			map.put("applytime", custchgau.getApplytime() == null ? "" : custchgau.getApplytime());
			map.put("audittime", custchgau.getAudittime() == null ? "" : custchgau.getAudittime());
			if(custchgau.getAuditret() != null)
				map.put("auditret", custchgau.getAuditret());
			map.put("comment", custchgau.getComment() == null ? "" : custchgau.getComment());
		}
	}
}