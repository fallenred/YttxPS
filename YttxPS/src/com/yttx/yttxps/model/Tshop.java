package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class Tshop {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_NO
     *
     * @mbggenerated
     */
    private String no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_REGIONNO
     *
     * @mbggenerated
     */
    private String regionno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_Desr
     *
     * @mbggenerated
     */
    private String desc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_OPENTIME
     *
     * @mbggenerated
     */
    private String opentime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_TEL
     *
     * @mbggenerated
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FD_SINGLERETURN
     *
     * @mbggenerated
     */
    private BigDecimal singlereturn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FD_TOTALRETURN
     *
     * @mbggenerated
     */
    private BigDecimal totalreturn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FD_MANTIP
     *
     * @mbggenerated
     */
    private BigDecimal mantip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FD_PARKTIP
     *
     * @mbggenerated
     */
    private BigDecimal parktip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FD_STAYTIME
     *
     * @mbggenerated
     */
    private BigDecimal staytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FS_POLICY
     *
     * @mbggenerated
     */
    private String policy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSHOP.FI_STAT
     *
     * @mbggenerated
     */
    private BigDecimal stat;
    
    private String scenicno;
    
    private String scenicname;
    
    /**
     * 地区名称
     */
    private String regionname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_NO
     *
     * @return the value of TSHOP.FS_NO
     *
     * @mbggenerated
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_NO
     *
     * @param no the value for TSHOP.FS_NO
     *
     * @mbggenerated
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_REGIONNO
     *
     * @return the value of TSHOP.FS_REGIONNO
     *
     * @mbggenerated
     */
    public String getRegionno() {
        return regionno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_REGIONNO
     *
     * @param regionno the value for TSHOP.FS_REGIONNO
     *
     * @mbggenerated
     */
    public void setRegionno(String regionno) {
        this.regionno = regionno == null ? null : regionno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_NAME
     *
     * @return the value of TSHOP.FS_NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_NAME
     *
     * @param name the value for TSHOP.FS_NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_desr
     *
     * @return the value of TSHOP.FS_desr
     *
     * @mbggenerated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_Desr
     *
     * @param desr the value for TSHOP.FS_Desr
     *
     * @mbggenerated
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_OPENTIME
     *
     * @return the value of TSHOP.FS_OPENTIME
     *
     * @mbggenerated
     */
    public String getOpentime() {
        return opentime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_OPENTIME
     *
     * @param opentime the value for TSHOP.FS_OPENTIME
     *
     * @mbggenerated
     */
    public void setOpentime(String opentime) {
        this.opentime = opentime == null ? null : opentime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_TEL
     *
     * @return the value of TSHOP.FS_TEL
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_TEL
     *
     * @param tel the value for TSHOP.FS_TEL
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FD_SINGLERETURN
     *
     * @return the value of TSHOP.FD_SINGLERETURN
     *
     * @mbggenerated
     */
    public BigDecimal getSinglereturn() {
        return singlereturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FD_SINGLERETURN
     *
     * @param singlereturn the value for TSHOP.FD_SINGLERETURN
     *
     * @mbggenerated
     */
    public void setSinglereturn(BigDecimal singlereturn) {
        this.singlereturn = singlereturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FD_TOTALRETURN
     *
     * @return the value of TSHOP.FD_TOTALRETURN
     *
     * @mbggenerated
     */
    public BigDecimal getTotalreturn() {
        return totalreturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FD_TOTALRETURN
     *
     * @param totalreturn the value for TSHOP.FD_TOTALRETURN
     *
     * @mbggenerated
     */
    public void setTotalreturn(BigDecimal totalreturn) {
        this.totalreturn = totalreturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FD_MANTIP
     *
     * @return the value of TSHOP.FD_MANTIP
     *
     * @mbggenerated
     */
    public BigDecimal getMantip() {
        return mantip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FD_MANTIP
     *
     * @param mantip the value for TSHOP.FD_MANTIP
     *
     * @mbggenerated
     */
    public void setMantip(BigDecimal mantip) {
        this.mantip = mantip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FD_PARKTIP
     *
     * @return the value of TSHOP.FD_PARKTIP
     *
     * @mbggenerated
     */
    public BigDecimal getParktip() {
        return parktip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FD_PARKTIP
     *
     * @param parktip the value for TSHOP.FD_PARKTIP
     *
     * @mbggenerated
     */
    public void setParktip(BigDecimal parktip) {
        this.parktip = parktip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FD_STAYTIME
     *
     * @return the value of TSHOP.FD_STAYTIME
     *
     * @mbggenerated
     */
    public BigDecimal getStaytime() {
        return staytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FD_STAYTIME
     *
     * @param staytime the value for TSHOP.FD_STAYTIME
     *
     * @mbggenerated
     */
    public void setStaytime(BigDecimal staytime) {
        this.staytime = staytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FS_POLICY
     *
     * @return the value of TSHOP.FS_POLICY
     *
     * @mbggenerated
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FS_POLICY
     *
     * @param policy the value for TSHOP.FS_POLICY
     *
     * @mbggenerated
     */
    public void setPolicy(String policy) {
        this.policy = policy == null ? null : policy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TSHOP.FI_STAT
     *
     * @return the value of TSHOP.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TSHOP.FI_STAT
     *
     * @param stat the value for TSHOP.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }

	public String getScenicno() {
		return scenicno;
	}

	public void setScenicno(String scenicno) {
		this.scenicno = scenicno;
	}

	public String getScenicname() {
		return scenicname;
	}

	public void setScenicname(String scenicname) {
		this.scenicname = scenicname;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
}