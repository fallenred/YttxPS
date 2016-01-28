package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Tticket {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fs_No
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private String fsNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fs_Name
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private String fsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fs_ScenicNo
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private String fsScenicno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fs_Type
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private String fsType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fi_Seq
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private Integer fiSeq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fs_Desc
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private String fsDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Full_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFullLowQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Half_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdHalfLowQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Child_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdChildLowQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Free_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFreeLowQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Full_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFullLowTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Half_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdHalfLowTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Child_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdChildLowTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Free_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFreeLowTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Full_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFullPeakQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Half_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdHalfPeakQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Child_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdChildPeakQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Free_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFreePeakQp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Full_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFullPeakTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Half_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdHalfPeakTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Child_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdChildPeakTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fd_Free_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private BigDecimal fdFreePeakTp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tticket.fi_Stat
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    private Integer fiStat;
    
    private List<TCCPrice> tccPrices;
    
    private Date ftStartdate;
    
    private Date ftEnddate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fs_No
     *
     * @return the value of tticket.fs_No
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public String getFsNo() {
        return fsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fs_No
     *
     * @param fsNo the value for tticket.fs_No
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFsNo(String fsNo) {
        this.fsNo = fsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fs_Name
     *
     * @return the value of tticket.fs_Name
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public String getFsName() {
        return fsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fs_Name
     *
     * @param fsName the value for tticket.fs_Name
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fs_ScenicNo
     *
     * @return the value of tticket.fs_ScenicNo
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public String getFsScenicno() {
        return fsScenicno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fs_ScenicNo
     *
     * @param fsScenicno the value for tticket.fs_ScenicNo
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFsScenicno(String fsScenicno) {
        this.fsScenicno = fsScenicno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fs_Type
     *
     * @return the value of tticket.fs_Type
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public String getFsType() {
        return fsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fs_Type
     *
     * @param fsType the value for tticket.fs_Type
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFsType(String fsType) {
        this.fsType = fsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fi_Seq
     *
     * @return the value of tticket.fi_Seq
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public Integer getFiSeq() {
        return fiSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fi_Seq
     *
     * @param fiSeq the value for tticket.fi_Seq
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFiSeq(Integer fiSeq) {
        this.fiSeq = fiSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fs_Desc
     *
     * @return the value of tticket.fs_Desc
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public String getFsDesc() {
        return fsDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fs_Desc
     *
     * @param fsDesc the value for tticket.fs_Desc
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFsDesc(String fsDesc) {
        this.fsDesc = fsDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Full_Low_QP
     *
     * @return the value of tticket.fd_Full_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFullLowQp() {
        return fdFullLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Full_Low_QP
     *
     * @param fdFullLowQp the value for tticket.fd_Full_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFullLowQp(BigDecimal fdFullLowQp) {
        this.fdFullLowQp = fdFullLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Half_Low_QP
     *
     * @return the value of tticket.fd_Half_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdHalfLowQp() {
        return fdHalfLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Half_Low_QP
     *
     * @param fdHalfLowQp the value for tticket.fd_Half_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdHalfLowQp(BigDecimal fdHalfLowQp) {
        this.fdHalfLowQp = fdHalfLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Child_Low_QP
     *
     * @return the value of tticket.fd_Child_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdChildLowQp() {
        return fdChildLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Child_Low_QP
     *
     * @param fdChildLowQp the value for tticket.fd_Child_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdChildLowQp(BigDecimal fdChildLowQp) {
        this.fdChildLowQp = fdChildLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Free_Low_QP
     *
     * @return the value of tticket.fd_Free_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFreeLowQp() {
        return fdFreeLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Free_Low_QP
     *
     * @param fdFreeLowQp the value for tticket.fd_Free_Low_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFreeLowQp(BigDecimal fdFreeLowQp) {
        this.fdFreeLowQp = fdFreeLowQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Full_Low_TP
     *
     * @return the value of tticket.fd_Full_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFullLowTp() {
        return fdFullLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Full_Low_TP
     *
     * @param fdFullLowTp the value for tticket.fd_Full_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFullLowTp(BigDecimal fdFullLowTp) {
        this.fdFullLowTp = fdFullLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Half_Low_TP
     *
     * @return the value of tticket.fd_Half_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdHalfLowTp() {
        return fdHalfLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Half_Low_TP
     *
     * @param fdHalfLowTp the value for tticket.fd_Half_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdHalfLowTp(BigDecimal fdHalfLowTp) {
        this.fdHalfLowTp = fdHalfLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Child_Low_TP
     *
     * @return the value of tticket.fd_Child_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdChildLowTp() {
        return fdChildLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Child_Low_TP
     *
     * @param fdChildLowTp the value for tticket.fd_Child_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdChildLowTp(BigDecimal fdChildLowTp) {
        this.fdChildLowTp = fdChildLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Free_Low_TP
     *
     * @return the value of tticket.fd_Free_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFreeLowTp() {
        return fdFreeLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Free_Low_TP
     *
     * @param fdFreeLowTp the value for tticket.fd_Free_Low_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFreeLowTp(BigDecimal fdFreeLowTp) {
        this.fdFreeLowTp = fdFreeLowTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Full_Peak_QP
     *
     * @return the value of tticket.fd_Full_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFullPeakQp() {
        return fdFullPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Full_Peak_QP
     *
     * @param fdFullPeakQp the value for tticket.fd_Full_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFullPeakQp(BigDecimal fdFullPeakQp) {
        this.fdFullPeakQp = fdFullPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Half_Peak_QP
     *
     * @return the value of tticket.fd_Half_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdHalfPeakQp() {
        return fdHalfPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Half_Peak_QP
     *
     * @param fdHalfPeakQp the value for tticket.fd_Half_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdHalfPeakQp(BigDecimal fdHalfPeakQp) {
        this.fdHalfPeakQp = fdHalfPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Child_Peak_QP
     *
     * @return the value of tticket.fd_Child_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdChildPeakQp() {
        return fdChildPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Child_Peak_QP
     *
     * @param fdChildPeakQp the value for tticket.fd_Child_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdChildPeakQp(BigDecimal fdChildPeakQp) {
        this.fdChildPeakQp = fdChildPeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Free_Peak_QP
     *
     * @return the value of tticket.fd_Free_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFreePeakQp() {
        return fdFreePeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Free_Peak_QP
     *
     * @param fdFreePeakQp the value for tticket.fd_Free_Peak_QP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFreePeakQp(BigDecimal fdFreePeakQp) {
        this.fdFreePeakQp = fdFreePeakQp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Full_Peak_TP
     *
     * @return the value of tticket.fd_Full_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFullPeakTp() {
        return fdFullPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Full_Peak_TP
     *
     * @param fdFullPeakTp the value for tticket.fd_Full_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFullPeakTp(BigDecimal fdFullPeakTp) {
        this.fdFullPeakTp = fdFullPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Half_Peak_TP
     *
     * @return the value of tticket.fd_Half_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdHalfPeakTp() {
        return fdHalfPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Half_Peak_TP
     *
     * @param fdHalfPeakTp the value for tticket.fd_Half_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdHalfPeakTp(BigDecimal fdHalfPeakTp) {
        this.fdHalfPeakTp = fdHalfPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Child_Peak_TP
     *
     * @return the value of tticket.fd_Child_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdChildPeakTp() {
        return fdChildPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Child_Peak_TP
     *
     * @param fdChildPeakTp the value for tticket.fd_Child_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdChildPeakTp(BigDecimal fdChildPeakTp) {
        this.fdChildPeakTp = fdChildPeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fd_Free_Peak_TP
     *
     * @return the value of tticket.fd_Free_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public BigDecimal getFdFreePeakTp() {
        return fdFreePeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fd_Free_Peak_TP
     *
     * @param fdFreePeakTp the value for tticket.fd_Free_Peak_TP
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFdFreePeakTp(BigDecimal fdFreePeakTp) {
        this.fdFreePeakTp = fdFreePeakTp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tticket.fi_Stat
     *
     * @return the value of tticket.fi_Stat
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public Integer getFiStat() {
        return fiStat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tticket.fi_Stat
     *
     * @param fiStat the value for tticket.fi_Stat
     *
     * @mbggenerated Thu Jan 07 18:18:56 CST 2016
     */
    public void setFiStat(Integer fiStat) {
        this.fiStat = fiStat;
    }

	public List<TCCPrice> getTccPrices() {
		return tccPrices;
	}

	public void setTccPrices(List<TCCPrice> tccPrices) {
		this.tccPrices = tccPrices;
	}

	public Date getFtStartdate() {
		return ftStartdate;
	}

	public void setFtStartdate(Date ftStartdate) {
		this.ftStartdate = ftStartdate;
	}

	public Date getFtEnddate() {
		return ftEnddate;
	}

	public void setFtEnddate(Date ftEnddate) {
		this.ftEnddate = ftEnddate;
	}
}