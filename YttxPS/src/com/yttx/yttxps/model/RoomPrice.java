package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;

public class RoomPrice {
    private String fsAccomno;

    private String fsRegionno;

    private String fsTacname;

    private String fsRoomtype;

    private String fsRoomname;

    private String fsRoommeal;

    private BigDecimal fiRoomstat;

    private Date ftStartdate;

    private Date ftEnddate;

    private String fsRestype;

    private String fsResno;

    private String fsCcno;

    private String fsCcname;

    private BigDecimal fdPrice;

    public String getFsAccomno() {
        return fsAccomno;
    }

    public void setFsAccomno(String fsAccomno) {
        this.fsAccomno = fsAccomno == null ? null : fsAccomno.trim();
    }

    public String getFsRegionno() {
        return fsRegionno;
    }

    public void setFsRegionno(String fsRegionno) {
        this.fsRegionno = fsRegionno == null ? null : fsRegionno.trim();
    }

    public String getFsTacname() {
        return fsTacname;
    }

    public void setFsTacname(String fsTacname) {
        this.fsTacname = fsTacname == null ? null : fsTacname.trim();
    }

    public String getFsRoomtype() {
        return fsRoomtype;
    }

    public void setFsRoomtype(String fsRoomtype) {
        this.fsRoomtype = fsRoomtype == null ? null : fsRoomtype.trim();
    }

    public String getFsRoomname() {
        return fsRoomname;
    }

    public void setFsRoomname(String fsRoomname) {
        this.fsRoomname = fsRoomname == null ? null : fsRoomname.trim();
    }

    public String getFsRoommeal() {
        return fsRoommeal;
    }

    public void setFsRoommeal(String fsRoommeal) {
        this.fsRoommeal = fsRoommeal == null ? null : fsRoommeal.trim();
    }

    public BigDecimal getFiRoomstat() {
        return fiRoomstat;
    }

    public void setFiRoomstat(BigDecimal fiRoomstat) {
        this.fiRoomstat = fiRoomstat;
    }

    public Date getFtStartdate() {
        return ftStartdate;
    }

    public void setFtStartdate(Date ftStartdate) {
        this.ftStartdate = ftStartdate;
    }

    public Date getFtEnddate() {
        return ftEnddate;
    }

    public void setFtEnddate(Date ftEnddate) {
        this.ftEnddate = ftEnddate;
    }

    public String getFsRestype() {
        return fsRestype;
    }

    public void setFsRestype(String fsRestype) {
        this.fsRestype = fsRestype == null ? null : fsRestype.trim();
    }

    public String getFsResno() {
        return fsResno;
    }

    public void setFsResno(String fsResno) {
        this.fsResno = fsResno == null ? null : fsResno.trim();
    }

    public String getFsCcno() {
        return fsCcno;
    }

    public void setFsCcno(String fsCcno) {
        this.fsCcno = fsCcno == null ? null : fsCcno.trim();
    }

    public String getFsCcname() {
        return fsCcname;
    }

    public void setFsCcname(String fsCcname) {
        this.fsCcname = fsCcname == null ? null : fsCcname.trim();
    }

    public BigDecimal getFdPrice() {
        return fdPrice;
    }

    public void setFdPrice(BigDecimal fdPrice) {
        this.fdPrice = fdPrice;
    }
}