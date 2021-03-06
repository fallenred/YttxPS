package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class Accomadation {
    
	
    private String no;
    private String regionno;
    private String regionname;
    private String starlvl;
    private String name;
    private String addr;
    private String speciality;
    private BigDecimal stat;
    private String desc;
    
    /**
     * 所属景区
     */
    private String fsScenicno;

    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_NO
     *
     * @param no the value for TACCOMADATION.FS_NO
     *
     * @mbggenerated
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FS_REGIONNO
     *
     * @return the value of TACCOMADATION.FS_REGIONNO
     *
     * @mbggenerated
     */
    public String getRegionno() {
        return regionno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_REGIONNO
     *
     * @param regionno the value for TACCOMADATION.FS_REGIONNO
     *
     * @mbggenerated
     */
    public void setRegionno(String regionno) {
        this.regionno = regionno == null ? null : regionno.trim();
    }

    public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FS_STARLVL
     *
     * @return the value of TACCOMADATION.FS_STARLVL
     *
     * @mbggenerated
     */
    public String getStarlvl() {
        return starlvl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_STARLVL
     *
     * @param starlvl the value for TACCOMADATION.FS_STARLVL
     *
     * @mbggenerated
     */
    public void setStarlvl(String starlvl) {
        this.starlvl = starlvl == null ? null : starlvl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FS_NAME
     *
     * @return the value of TACCOMADATION.FS_NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_NAME
     *
     * @param name the value for TACCOMADATION.FS_NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FS_ADDR
     *
     * @return the value of TACCOMADATION.FS_ADDR
     *
     * @mbggenerated
     */
    public String getAddr() {
        return addr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_ADDR
     *
     * @param addr the value for TACCOMADATION.FS_ADDR
     *
     * @mbggenerated
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FS_SPECIALITY
     *
     * @return the value of TACCOMADATION.FS_SPECIALITY
     *
     * @mbggenerated
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FS_SPECIALITY
     *
     * @param speciality the value for TACCOMADATION.FS_SPECIALITY
     *
     * @mbggenerated
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality == null ? null : speciality.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FI_STAT
     *
     * @return the value of TACCOMADATION.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FI_STAT
     *
     * @param stat the value for TACCOMADATION.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TACCOMADATION.FC_DESC
     *
     * @return the value of TACCOMADATION.FC_DESC
     *
     * @mbggenerated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TACCOMADATION.FC_DESC
     *
     * @param desc the value for TACCOMADATION.FC_DESC
     *
     * @mbggenerated
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

	public String getFsScenicno() {
		return fsScenicno;
	}

	public void setFsScenicno(String fsScenicno) {
		this.fsScenicno = fsScenicno;
	}
}