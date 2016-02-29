package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.List;

public class TRouteArrangeWithBLOBs extends TRouteArrange {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROUTEARRANGE.FC_SCHEDULE
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    private String fcSchedule;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROUTEARRANGE.FC_RESSNAPSHOT
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    private String fcRessnapshot;
    
    private BigDecimal fiDayflag;
    
    private List<TRouteCCKey> routecc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROUTEARRANGE.FC_SCHEDULE
     *
     * @return the value of TROUTEARRANGE.FC_SCHEDULE
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    public String getFcSchedule() {
        return fcSchedule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROUTEARRANGE.FC_SCHEDULE
     *
     * @param fcSchedule the value for TROUTEARRANGE.FC_SCHEDULE
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    public void setFcSchedule(String fcSchedule) {
        this.fcSchedule = fcSchedule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROUTEARRANGE.FC_RESSNAPSHOT
     *
     * @return the value of TROUTEARRANGE.FC_RESSNAPSHOT
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    public String getFcRessnapshot() {
        return fcRessnapshot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROUTEARRANGE.FC_RESSNAPSHOT
     *
     * @param fcRessnapshot the value for TROUTEARRANGE.FC_RESSNAPSHOT
     *
     * @mbggenerated Thu Feb 04 10:26:31 CST 2016
     */
    public void setFcRessnapshot(String fcRessnapshot) {
        this.fcRessnapshot = fcRessnapshot;
    }

	public List<TRouteCCKey> getRoutecc() {
		return routecc;
	}

	public void setRoutecc(List<TRouteCCKey> routecc) {
		this.routecc = routecc;
	}

	public BigDecimal getFiDayflag() {
		return fiDayflag;
	}

	public void setFiDayflag(BigDecimal fiDayflag) {
		this.fiDayflag = fiDayflag;
	}
}