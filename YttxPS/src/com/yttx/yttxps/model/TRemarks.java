package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;

public class TRemarks extends TRemarksKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FS_OPER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private String fsOperId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FT_DATE
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private Date ftDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FS_CONTENT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private String fsContent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FD_AMT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private BigDecimal fdAmt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FI_STAT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private BigDecimal fiStat;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FS_OPER_ID
	 * @return  the value of TREMARKS.FS_OPER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public String getFsOperId() {
		return fsOperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FS_OPER_ID
	 * @param fsOperId  the value for TREMARKS.FS_OPER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFsOperId(String fsOperId) {
		this.fsOperId = fsOperId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FT_DATE
	 * @return  the value of TREMARKS.FT_DATE
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public Date getFtDate() {
		return ftDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FT_DATE
	 * @param ftDate  the value for TREMARKS.FT_DATE
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFtDate(Date ftDate) {
		this.ftDate = ftDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FS_CONTENT
	 * @return  the value of TREMARKS.FS_CONTENT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public String getFsContent() {
		return fsContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FS_CONTENT
	 * @param fsContent  the value for TREMARKS.FS_CONTENT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFsContent(String fsContent) {
		this.fsContent = fsContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FD_AMT
	 * @return  the value of TREMARKS.FD_AMT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public BigDecimal getFdAmt() {
		return fdAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FD_AMT
	 * @param fdAmt  the value for TREMARKS.FD_AMT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFdAmt(BigDecimal fdAmt) {
		this.fdAmt = fdAmt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FI_STAT
	 * @return  the value of TREMARKS.FI_STAT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public BigDecimal getFiStat() {
		return fiStat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FI_STAT
	 * @param fiStat  the value for TREMARKS.FI_STAT
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFiStat(BigDecimal fiStat) {
		this.fiStat = fiStat;
	}
}