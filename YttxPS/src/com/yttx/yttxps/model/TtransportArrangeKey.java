package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;

public class TtransportArrangeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TTRANSPORTARRANGE.FI_GENINDEX
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    private BigDecimal fiGenindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TTRANSPORTARRANGE.FS_TRANSNO
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    private String fsTransno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TTRANSPORTARRANGE.FT_ENDDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    private Date ftEnddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TTRANSPORTARRANGE.FT_STARTDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    private Date ftStartdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TTRANSPORTARRANGE.FI_GENINDEX
     *
     * @return the value of TTRANSPORTARRANGE.FI_GENINDEX
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public BigDecimal getFiGenindex() {
        return fiGenindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TTRANSPORTARRANGE.FI_GENINDEX
     *
     * @param fiGenindex the value for TTRANSPORTARRANGE.FI_GENINDEX
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public void setFiGenindex(BigDecimal fiGenindex) {
        this.fiGenindex = fiGenindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TTRANSPORTARRANGE.FS_TRANSNO
     *
     * @return the value of TTRANSPORTARRANGE.FS_TRANSNO
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public String getFsTransno() {
        return fsTransno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TTRANSPORTARRANGE.FS_TRANSNO
     *
     * @param fsTransno the value for TTRANSPORTARRANGE.FS_TRANSNO
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public void setFsTransno(String fsTransno) {
        this.fsTransno = fsTransno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TTRANSPORTARRANGE.FT_ENDDATE
     *
     * @return the value of TTRANSPORTARRANGE.FT_ENDDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public Date getFtEnddate() {
        return ftEnddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TTRANSPORTARRANGE.FT_ENDDATE
     *
     * @param ftEnddate the value for TTRANSPORTARRANGE.FT_ENDDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public void setFtEnddate(Date ftEnddate) {
        this.ftEnddate = ftEnddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TTRANSPORTARRANGE.FT_STARTDATE
     *
     * @return the value of TTRANSPORTARRANGE.FT_STARTDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public Date getFtStartdate() {
        return ftStartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TTRANSPORTARRANGE.FT_STARTDATE
     *
     * @param ftStartdate the value for TTRANSPORTARRANGE.FT_STARTDATE
     *
     * @mbggenerated Sun Jan 10 16:31:14 CST 2016
     */
    public void setFtStartdate(Date ftStartdate) {
        this.ftStartdate = ftStartdate;
    }
}