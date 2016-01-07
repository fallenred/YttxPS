package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class Troom {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FI_INDEX
     *
     * @mbggenerated
     */
    private BigDecimal index;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FS_ACCOMNO
     *
     * @mbggenerated
     */
    private String accomno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FS_TYPE
     *
     * @mbggenerated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FS_MEAL
     *
     * @mbggenerated
     */
    private String meal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FD_PRICE
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TROOM.FI_STAT
     *
     * @mbggenerated
     */
    private BigDecimal stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FI_INDEX
     *
     * @return the value of TROOM.FI_INDEX
     *
     * @mbggenerated
     */
    public BigDecimal getIndex() {
        return index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FI_INDEX
     *
     * @param index the value for TROOM.FI_INDEX
     *
     * @mbggenerated
     */
    public void setIndex(BigDecimal index) {
        this.index = index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FS_ACCOMNO
     *
     * @return the value of TROOM.FS_ACCOMNO
     *
     * @mbggenerated
     */
    public String getAccomno() {
        return accomno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FS_ACCOMNO
     *
     * @param accomno the value for TROOM.FS_ACCOMNO
     *
     * @mbggenerated
     */
    public void setAccomno(String accomno) {
        this.accomno = accomno == null ? null : accomno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FS_TYPE
     *
     * @return the value of TROOM.FS_TYPE
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FS_TYPE
     *
     * @param type the value for TROOM.FS_TYPE
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FS_MEAL
     *
     * @return the value of TROOM.FS_MEAL
     *
     * @mbggenerated
     */
    public String getMeal() {
        return meal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FS_MEAL
     *
     * @param meal the value for TROOM.FS_MEAL
     *
     * @mbggenerated
     */
    public void setMeal(String meal) {
        this.meal = meal == null ? null : meal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FD_PRICE
     *
     * @return the value of TROOM.FD_PRICE
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FD_PRICE
     *
     * @param price the value for TROOM.FD_PRICE
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TROOM.FI_STAT
     *
     * @return the value of TROOM.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TROOM.FI_STAT
     *
     * @param stat the value for TROOM.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }
}