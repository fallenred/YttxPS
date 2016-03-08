package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;
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

    private BigDecimal stat;

    private String auditno;

    private String legalPerson;
    
    private String  fax;
    
    private String address;
    
    private String tabLicense;
    
    private String salesManID;

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

    
    public BigDecimal getStat() {
        return stat;
    }

    
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }

   
    public String getAuditno() {
        return auditno;
    }

    public void setAuditno(String auditno) {
        this.auditno = auditno == null ? null : auditno.trim();
    }


	public String getRegTimeDesc() {
		return regTimeDesc;
	}


	public void setRegTimeDesc(String regTimeDesc) {
		this.regTimeDesc = regTimeDesc == null ? null : regTimeDesc.trim();
	}


	public String getTimeStampDesc() {
		return timeStampDesc;
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
    
    
}