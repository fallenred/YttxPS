package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.yttx.comm.YttxJsonDateSerializer;

public class Driver implements
java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDRIVER.FI_INDEX
     *
     * @mbggenerated
     */
    private BigDecimal index;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDRIVER.FS_NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDRIVER.FI_GENDER
     *
     * @mbggenerated
     */
    private BigDecimal gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDRIVER.FT_BIRTH
     *
     * @mbggenerated
     */
    private Date birth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDRIVER.FI_STAT
     *
     * @mbggenerated
     */
    private BigDecimal stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TDRIVER.FI_INDEX
     *
     * @return the value of TDRIVER.FI_INDEX
     *
     * @mbggenerated
     */
    public BigDecimal getIndex() {
        return index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TDRIVER.FI_INDEX
     *
     * @param index the value for TDRIVER.FI_INDEX
     *
     * @mbggenerated
     */
    public void setIndex(BigDecimal index) {
        this.index = index;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TDRIVER.FS_NAME
     *
     * @return the value of TDRIVER.FS_NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TDRIVER.FS_NAME
     *
     * @param name the value for TDRIVER.FS_NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TDRIVER.FI_GENDER
     *
     * @return the value of TDRIVER.FI_GENDER
     *
     * @mbggenerated
     */
    public BigDecimal getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TDRIVER.FI_GENDER
     *
     * @param gender the value for TDRIVER.FI_GENDER
     *
     * @mbggenerated
     */
    public void setGender(BigDecimal gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TDRIVER.FT_BIRTH
     *
     * @return the value of TDRIVER.FT_BIRTH
     *
     * @mbggenerated
     */
    @JsonSerialize(using = YttxJsonDateSerializer.class)
    public Date getBirth() {
        return birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TDRIVER.FT_BIRTH
     *
     * @param birth the value for TDRIVER.FT_BIRTH
     *
     * @mbggenerated
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TDRIVER.FI_STAT
     *
     * @return the value of TDRIVER.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TDRIVER.FI_STAT
     *
     * @param stat the value for TDRIVER.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }
    
    @Override
    public String toString() {
    	return name+" "+gender+" "+stat+" "+birth;
    }
}