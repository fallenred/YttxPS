package com.yttx.yttxps.model;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class TRestaurant {
    
    private String no;

    private String name;

    private String regionno;
    
    private String regionname;

    private String addr;

    private String menu;
    
    private MultipartFile menuImgFile;
    
    private String menuImgFileLoction;
    
    private String menuDesc;

    private BigDecimal scale;
    
    private BigDecimal minScale;
    
    private BigDecimal maxScale;

    private String special;

    private String attention;

    private String lvl;

    private BigDecimal stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_NO
     *
     * @return the value of TRESTAURANT.FS_NO
     *
     * @mbggenerated
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_NO
     *
     * @param no the value for TRESTAURANT.FS_NO
     *
     * @mbggenerated
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_NAME
     *
     * @return the value of TRESTAURANT.FS_NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_NAME
     *
     * @param name the value for TRESTAURANT.FS_NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_REGIONNO
     *
     * @return the value of TRESTAURANT.FS_REGIONNO
     *
     * @mbggenerated
     */
    public String getRegionno() {
        return regionno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_REGIONNO
     *
     * @param regionno the value for TRESTAURANT.FS_REGIONNO
     *
     * @mbggenerated
     */
    public void setRegionno(String regionno) {
        this.regionno = regionno == null ? null : regionno.trim();
    }

    public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_ADDR
     *
     * @return the value of TRESTAURANT.FS_ADDR
     *
     * @mbggenerated
     */
    public String getAddr() {
        return addr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_ADDR
     *
     * @param addr the value for TRESTAURANT.FS_ADDR
     *
     * @mbggenerated
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_MENU
     *
     * @return the value of TRESTAURANT.FS_MENU
     *
     * @mbggenerated
     */
    public String getMenu() {
        return menu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_MENU
     *
     * @param menu the value for TRESTAURANT.FS_MENU
     *
     * @mbggenerated
     */
    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FI_SCALE
     *
     * @return the value of TRESTAURANT.FI_SCALE
     *
     * @mbggenerated
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FI_SCALE
     *
     * @param scale the value for TRESTAURANT.FI_SCALE
     *
     * @mbggenerated
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    
    public BigDecimal getMinScale() {
		return minScale;
	}

	public void setMinScale(BigDecimal minScale) {
		this.minScale = minScale;
	}

	public BigDecimal getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(BigDecimal maxScale) {
		this.maxScale = maxScale;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_SPECIAL
     *
     * @return the value of TRESTAURANT.FS_SPECIAL
     *
     * @mbggenerated
     */
    public String getSpecial() {
        return special;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_SPECIAL
     *
     * @param special the value for TRESTAURANT.FS_SPECIAL
     *
     * @mbggenerated
     */
    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_ATTENTION
     *
     * @return the value of TRESTAURANT.FS_ATTENTION
     *
     * @mbggenerated
     */
    public String getAttention() {
        return attention;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_ATTENTION
     *
     * @param attention the value for TRESTAURANT.FS_ATTENTION
     *
     * @mbggenerated
     */
    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FS_LVL
     *
     * @return the value of TRESTAURANT.FS_LVL
     *
     * @mbggenerated
     */
    public String getLvl() {
        return lvl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FS_LVL
     *
     * @param lvl the value for TRESTAURANT.FS_LVL
     *
     * @mbggenerated
     */
    public void setLvl(String lvl) {
        this.lvl = lvl == null ? null : lvl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TRESTAURANT.FI_STAT
     *
     * @return the value of TRESTAURANT.FI_STAT
     *
     * @mbggenerated
     */
    public BigDecimal getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TRESTAURANT.FI_STAT
     *
     * @param stat the value for TRESTAURANT.FI_STAT
     *
     * @mbggenerated
     */
    public void setStat(BigDecimal stat) {
        this.stat = stat;
    }
    
    
    

	public MultipartFile getMenuImgFile() {
		return menuImgFile;
	}

	public void setMenuImgFile(MultipartFile menuImgFile) {
		this.menuImgFile = menuImgFile;
	}

	public String getMenuImgFileLoction() {
		return menuImgFileLoction;
	}

	public void setMenuImgFileLoction(String menuImgFileLoction) {
		this.menuImgFileLoction = (menuImgFileLoction ==null?null:menuImgFileLoction.trim());
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = (menuDesc == null?null:menuDesc.trim());
	}

	@Override
	public String toString() {
		return "TRestaurant [no=" + no + ", name=" + name + ", regionno=" + regionno + ", addr=" + addr + ", menu="
				+ menu + ", scale=" + scale + ", minScale=" + minScale + ", maxScale=" + maxScale + ", special="
				+ special + ", attention=" + attention + ", lvl=" + lvl + ", stat=" + stat + "]";
	}
}