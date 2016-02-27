package com.yttx.yttxps.model;

import java.util.Date;
import java.util.List;

import com.yttx.yttxps.xml.bean.Body;

public class TOrderCustomWithBLOBs extends TOrderCustom {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDERCUSTOM.FC_RESSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    private String fcRessnapshot;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDERCUSTOM.FC_FUZZYSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    private String fcFuzzysnapshot;
    
    /**
     * 线路id
     */
    private String fiGenindex;
    
    /**
     * 订单名称
     */
    private String fsName;
    
    /**
     * 发团日期
     */
    private Date ftStartdate;
    
    /**
     * 快照资源
     */
    private Body body;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TORDERCUSTOM.FC_RESSNAPSHOT
     *
     * @return the value of TORDERCUSTOM.FC_RESSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    public String getFcRessnapshot() {
        return fcRessnapshot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TORDERCUSTOM.FC_RESSNAPSHOT
     *
     * @param fcRessnapshot the value for TORDERCUSTOM.FC_RESSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    public void setFcRessnapshot(String fcRessnapshot) {
        this.fcRessnapshot = fcRessnapshot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TORDERCUSTOM.FC_FUZZYSNAPSHOT
     *
     * @return the value of TORDERCUSTOM.FC_FUZZYSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    public String getFcFuzzysnapshot() {
        return fcFuzzysnapshot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TORDERCUSTOM.FC_FUZZYSNAPSHOT
     *
     * @param fcFuzzysnapshot the value for TORDERCUSTOM.FC_FUZZYSNAPSHOT
     *
     * @mbggenerated Sun Feb 21 14:08:05 CST 2016
     */
    public void setFcFuzzysnapshot(String fcFuzzysnapshot) {
        this.fcFuzzysnapshot = fcFuzzysnapshot;
    }

	public String getFiGenindex() {
		return fiGenindex;
	}

	public void setFiGenindex(String fiGenindex) {
		this.fiGenindex = fiGenindex;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public Date getFtStartdate() {
		return ftStartdate;
	}

	public void setFtStartdate(Date ftStartdate) {
		this.ftStartdate = ftStartdate;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}