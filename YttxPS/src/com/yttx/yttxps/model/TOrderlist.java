package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yttx.yttxps.xml.bean.Reslist;

public class TOrderlist {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_NO
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FI_GENINDEX
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fiGenindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_NAME
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_USER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsUserId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_USER_SUBID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsUserSubid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_OPER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsOperId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FT_CREATDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String ftCreatdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_TYPE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_ROUTE_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsRouteId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_PROPERTY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsProperty;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FI_DAYS
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fiDays;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FT_STARTDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private Date ftStartdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_STARTPLACE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsStartplace;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FD_PRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fdPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_SUMMARY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsSummary;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FD_TOTALFEE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fdTotalfee;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FD_PAIDAMT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fdPaidamt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_REMARK
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsRemark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FI_STAT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fiStat;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FS_DAC
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private String fsDac;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FI_VISITORNUM
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fiVisitornum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TORDERLIST.FD_INSUERPRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	private BigDecimal fdInsuerprice;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_NO
	 * @return  the value of TORDERLIST.FS_NO
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsNo() {
		return fsNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_NO
	 * @param fsNo  the value for TORDERLIST.FS_NO
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsNo(String fsNo) {
		this.fsNo = fsNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FI_GENINDEX
	 * @return  the value of TORDERLIST.FI_GENINDEX
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFiGenindex() {
		return fiGenindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FI_GENINDEX
	 * @param fiGenindex  the value for TORDERLIST.FI_GENINDEX
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFiGenindex(BigDecimal fiGenindex) {
		this.fiGenindex = fiGenindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_NAME
	 * @return  the value of TORDERLIST.FS_NAME
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsName() {
		return fsName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_NAME
	 * @param fsName  the value for TORDERLIST.FS_NAME
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_USER_ID
	 * @return  the value of TORDERLIST.FS_USER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsUserId() {
		return fsUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_USER_ID
	 * @param fsUserId  the value for TORDERLIST.FS_USER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsUserId(String fsUserId) {
		this.fsUserId = fsUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_USER_SUBID
	 * @return  the value of TORDERLIST.FS_USER_SUBID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsUserSubid() {
		return fsUserSubid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_USER_SUBID
	 * @param fsUserSubid  the value for TORDERLIST.FS_USER_SUBID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsUserSubid(String fsUserSubid) {
		this.fsUserSubid = fsUserSubid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_OPER_ID
	 * @return  the value of TORDERLIST.FS_OPER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsOperId() {
		return fsOperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_OPER_ID
	 * @param fsOperId  the value for TORDERLIST.FS_OPER_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsOperId(String fsOperId) {
		this.fsOperId = fsOperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FT_CREATDATE
	 * @return  the value of TORDERLIST.FT_CREATDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFtCreatdate() {
		return ftCreatdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FT_CREATDATE
	 * @param ftCreatdate  the value for TORDERLIST.FT_CREATDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFtCreatdate(String ftCreatdate) {
		this.ftCreatdate = ftCreatdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_TYPE
	 * @return  the value of TORDERLIST.FS_TYPE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsType() {
		return fsType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_TYPE
	 * @param fsType  the value for TORDERLIST.FS_TYPE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_ROUTE_ID
	 * @return  the value of TORDERLIST.FS_ROUTE_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsRouteId() {
		return fsRouteId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_ROUTE_ID
	 * @param fsRouteId  the value for TORDERLIST.FS_ROUTE_ID
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsRouteId(String fsRouteId) {
		this.fsRouteId = fsRouteId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_PROPERTY
	 * @return  the value of TORDERLIST.FS_PROPERTY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsProperty() {
		return fsProperty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_PROPERTY
	 * @param fsProperty  the value for TORDERLIST.FS_PROPERTY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsProperty(String fsProperty) {
		this.fsProperty = fsProperty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FI_DAYS
	 * @return  the value of TORDERLIST.FI_DAYS
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFiDays() {
		return fiDays;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FI_DAYS
	 * @param fiDays  the value for TORDERLIST.FI_DAYS
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFiDays(BigDecimal fiDays) {
		this.fiDays = fiDays;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FT_STARTDATE
	 * @return  the value of TORDERLIST.FT_STARTDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public Date getFtStartdate() {
		return ftStartdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FT_STARTDATE
	 * @param ftStartdate  the value for TORDERLIST.FT_STARTDATE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFtStartdate(Date ftStartdate) {
		this.ftStartdate = ftStartdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_STARTPLACE
	 * @return  the value of TORDERLIST.FS_STARTPLACE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsStartplace() {
		return fsStartplace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_STARTPLACE
	 * @param fsStartplace  the value for TORDERLIST.FS_STARTPLACE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsStartplace(String fsStartplace) {
		this.fsStartplace = fsStartplace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FD_PRICE
	 * @return  the value of TORDERLIST.FD_PRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFdPrice() {
		return fdPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FD_PRICE
	 * @param fdPrice  the value for TORDERLIST.FD_PRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFdPrice(BigDecimal fdPrice) {
		this.fdPrice = fdPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_SUMMARY
	 * @return  the value of TORDERLIST.FS_SUMMARY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsSummary() {
		return fsSummary;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_SUMMARY
	 * @param fsSummary  the value for TORDERLIST.FS_SUMMARY
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsSummary(String fsSummary) {
		this.fsSummary = fsSummary;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FD_TOTALFEE
	 * @return  the value of TORDERLIST.FD_TOTALFEE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFdTotalfee() {
		return fdTotalfee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FD_TOTALFEE
	 * @param fdTotalfee  the value for TORDERLIST.FD_TOTALFEE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFdTotalfee(BigDecimal fdTotalfee) {
		this.fdTotalfee = fdTotalfee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FD_PAIDAMT
	 * @return  the value of TORDERLIST.FD_PAIDAMT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFdPaidamt() {
		return fdPaidamt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FD_PAIDAMT
	 * @param fdPaidamt  the value for TORDERLIST.FD_PAIDAMT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFdPaidamt(BigDecimal fdPaidamt) {
		this.fdPaidamt = fdPaidamt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_REMARK
	 * @return  the value of TORDERLIST.FS_REMARK
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsRemark() {
		return fsRemark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_REMARK
	 * @param fsRemark  the value for TORDERLIST.FS_REMARK
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsRemark(String fsRemark) {
		this.fsRemark = fsRemark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FI_STAT
	 * @return  the value of TORDERLIST.FI_STAT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFiStat() {
		return fiStat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FI_STAT
	 * @param fiStat  the value for TORDERLIST.FI_STAT
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFiStat(String fiStat) {
		this.fiStat = fiStat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FS_DAC
	 * @return  the value of TORDERLIST.FS_DAC
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public String getFsDac() {
		return fsDac;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FS_DAC
	 * @param fsDac  the value for TORDERLIST.FS_DAC
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFsDac(String fsDac) {
		this.fsDac = fsDac;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FI_VISITORNUM
	 * @return  the value of TORDERLIST.FI_VISITORNUM
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFiVisitornum() {
		return fiVisitornum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FI_VISITORNUM
	 * @param fiVisitornum  the value for TORDERLIST.FI_VISITORNUM
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFiVisitornum(BigDecimal fiVisitornum) {
		this.fiVisitornum = fiVisitornum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TORDERLIST.FD_INSUERPRICE
	 * @return  the value of TORDERLIST.FD_INSUERPRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public BigDecimal getFdInsuerprice() {
		return fdInsuerprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TORDERLIST.FD_INSUERPRICE
	 * @param fdInsuerprice  the value for TORDERLIST.FD_INSUERPRICE
	 * @mbggenerated  Sat Mar 19 11:12:30 CST 2016
	 */
	public void setFdInsuerprice(BigDecimal fdInsuerprice) {
		this.fdInsuerprice = fdInsuerprice;
	}

	private List<Reslist> reslist;
	
	public List<Reslist> getReslist() {
		return reslist;
	}

	public void setReslist(List<Reslist> reslist) {
		this.reslist = reslist;
	}

}