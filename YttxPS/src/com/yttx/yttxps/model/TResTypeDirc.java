package com.yttx.yttxps.model;

public class TResTypeDirc {
    private String fsRestype;

    private String fsResname;

    private String fsResprop;

    public String getFsRestype() {
        return fsRestype;
    }

    public void setFsRestype(String fsRestype) {
        this.fsRestype = fsRestype == null ? null : fsRestype.trim();
    }

    public String getFsResname() {
        return fsResname;
    }

    public void setFsResname(String fsResname) {
        this.fsResname = fsResname == null ? null : fsResname.trim();
    }

    public String getFsResprop() {
        return fsResprop;
    }

    public void setFsResprop(String fsResprop) {
        this.fsResprop = fsResprop == null ? null : fsResprop.trim();
    }
}