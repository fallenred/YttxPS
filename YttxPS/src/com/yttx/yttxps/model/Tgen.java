package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class Tgen {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TGEN.FI_INDEX
     *
     * @mbggenerated
     */
    private BigDecimal index;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TGEN.FS_NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TGEN.FI_DAYS
     *
     * @mbggenerated
     */
    private BigDecimal days;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TGEN.FI_STAT
     *
     * @mbggenerated
     */
    private BigDecimal stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TGEN.FI_INDEX
     *
     * @return the value of TGEN.FI_INDEX
     *
     * @mbggenerated
     */
    public BigDecimal getIndex() {
        return index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TGEN.FI_INDEX
     *
     * @param index the value for TGEN.FI_INDEX
     *
     * @mbggenerated
     */
    public void setIndex(BigDecimal index) {
        this.index = index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TGEN.FS_NAME
     *
     * @return the value of TGEN.FS_NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TGEN.FS_NAME
     *
     * @param name the value for TGEN.FS_NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TGEN.FI_DAYS
     *
     * @return the value of TGEN.FI_DAYS
     *
     * @mbggenerated
     */
    public BigDecimal getDays() {
        return days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TGEN.FI_DAYS
     *
     * @param days the value for TGEN.FI_DAYS
     *
     * @mbggenerated
     */
    public void setDays(BigDecimal days) {
        this.days = days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TGEN.FI_STAT
     *
     * @return the value of TGEN.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TGEN.FI_STAT
     *
     * @param stat the value for TGEN.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }
}