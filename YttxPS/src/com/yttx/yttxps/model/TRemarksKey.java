package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class TRemarksKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FI_SEQ
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private BigDecimal fiSeq;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column TREMARKS.FS_ORDER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	private String fsOrderId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FI_SEQ
	 * @return  the value of TREMARKS.FI_SEQ
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public BigDecimal getFiSeq() {
		return fiSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FI_SEQ
	 * @param fiSeq  the value for TREMARKS.FI_SEQ
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFiSeq(BigDecimal fiSeq) {
		this.fiSeq = fiSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column TREMARKS.FS_ORDER_ID
	 * @return  the value of TREMARKS.FS_ORDER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public String getFsOrderId() {
		return fsOrderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column TREMARKS.FS_ORDER_ID
	 * @param fsOrderId  the value for TREMARKS.FS_ORDER_ID
	 * @mbggenerated  Sun Jan 24 19:51:04 CST 2016
	 */
	public void setFsOrderId(String fsOrderId) {
		this.fsOrderId = fsOrderId;
	}
}