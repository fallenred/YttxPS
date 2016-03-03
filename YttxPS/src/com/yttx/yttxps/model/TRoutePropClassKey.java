package com.yttx.yttxps.model;

import java.math.BigDecimal;

public class TRoutePropClassKey {
    private String fsId;

    private BigDecimal fiClass;

    public String getFsId() {
        return fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId == null ? null : fsId.trim();
    }

    public BigDecimal getFiClass() {
        return fiClass;
    }

    public void setFiClass(BigDecimal fiClass) {
        this.fiClass = fiClass;
    }
}