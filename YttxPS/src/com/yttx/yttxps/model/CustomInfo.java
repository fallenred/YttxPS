package com.yttx.yttxps.model;

import java.util.Date;

import com.yttx.comm.DateUtil;
/**
 * 会员管理模块--会员bean
 */
public class CustomInfo {
	
    private String id;

    private String name;

    private String taname;

    private String licenceno;

    private String taxlicence;

    private String contact;

    private String certid;

    private String email;

    private Date regtime;
    
    private String regTimeDesc;

    private Date timestamp;
    
    private String timeStampDesc;

    private String dac;

    private Integer stat;

    private String auditno;

    private String legalPerson;
    
    private String  fax;
    
    private String address;
    
    private String tabLicense;
    
    private String salesManID;
    
    private Integer auditType;
    
    private Date applyTime;
    
    private String applyTimeDesc;
    
    private String auditor;
  
    private Date auditTime;
    
    private String auditTimeDesc;
    
    private Integer auditRet = 0;
    
    private String comment;


    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

   
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

   
    public String getTaname() {
        return taname;
    }

   
    public void setTaname(String taname) {
        this.taname = taname == null ? null : taname.trim();
    }

    public String getLicenceno() {
        return licenceno;
    }

    
    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno == null ? null : licenceno.trim();
    }

   
    public String getTaxlicence() {
        return taxlicence;
    }

    
    public void setTaxlicence(String taxlicence) {
        this.taxlicence = taxlicence == null ? null : taxlicence.trim();
    }

   
    public String getContact() {
        return contact;
    }

    
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

   
    public String getCertid() {
        return certid;
    }

    
    public void setCertid(String certid) {
        this.certid = certid == null ? null : certid.trim();
    }

    public String getEmail() {
        return email;
    }

 
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

   
    public Date getRegtime() {
        return regtime;
    }

   
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    
    public Date getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    
    public String getDac() {
        return dac;
    }

    
    public void setDac(String dac) {
        this.dac = dac == null ? null : dac.trim();
    }

    
    public Integer getStat() {
        return stat;
    }

    
    public void setStat(Integer stat) {
        this.stat = stat;
    }

   
    public String getAuditno() {
        return auditno;
    }

    public void setAuditno(String auditno) {
        this.auditno = auditno == null ? null : auditno.trim();
    }


	public String getRegTimeDesc() {
		return DateUtil.getFullTimeFormatStr(regtime);
	}


	public void setRegTimeDesc(String regTimeDesc) {
		this.regTimeDesc = regTimeDesc == null ? null : regTimeDesc.trim();
	}


	public String getTimeStampDesc() {
		return DateUtil.getFullTimeFormatStr(timestamp);
	}


	public void setTimeStampDesc(String timeStampDesc) {
		this.timeStampDesc = timeStampDesc== null ? null :timeStampDesc.trim() ;
	}


	public String getLegalPerson() {
		return legalPerson;
	}


	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax == null ? null :fax.trim() ;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}


	public String getTabLicense() {
		return tabLicense;
	}


	public void setTabLicense(String tabLicense) {
		this.tabLicense = tabLicense == null ? null : tabLicense.trim();
	}


	public String getSalesManID() {
		return salesManID;
	}


	public void setSalesManID(String salesManID) {
		this.salesManID = salesManID == null ? null :salesManID.trim() ;
	}


	public Integer getAuditType() {
		return auditType;
	}


	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}


	public Date getApplyTime() {
		return applyTime;
	}


	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}


	public String getApplyTimeDesc() {
		return DateUtil.getFullTimeFormatStr(applyTime);
	}


	public void setApplyTimeDesc(String applyTimeDesc) {
		this.applyTimeDesc = applyTimeDesc==null?null : applyTimeDesc.trim();
	}


	public String getAuditor() {
		return auditor;
	}


	public void setAuditor(String auditor) {
		this.auditor = auditor ==null?null:auditor.trim();
	}


	public Date getAuditTime() {
		return auditTime;
	}


	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}


	public String getAuditTimeDesc() {
		return DateUtil.getFullTimeFormatStr(auditTime);
	}


	public void setAuditTimeDesc(String auditTimeDesc) {
		this.auditTimeDesc = auditTimeDesc==null?null:auditTimeDesc.trim();
	}


	public Integer getAuditRet() {
		return auditRet;
	}


	public void setAuditRet(Integer auditRet) {
		this.auditRet = auditRet;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment == null?null:comment.trim();
	}
}