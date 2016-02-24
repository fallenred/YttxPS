package com.yttx.yttxps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TEntertainment {
    private String fsNo;

    private String fsName;

    private String fsRegionno;

    private String fsAddr;

    private String fsType;

    private String fsDesc;

    private String fsLvl;

    private String fsOpentime;

    private BigDecimal fiStat;

    private Date ftStartdate;

    private Date ftEnddate;

    private String fsResno;

    private Short fdfulllowqp;

    private Short fdhalflowqp;

    private Short fdchildlowqp;

    private Short fdfreelowqp;

    private Short fdfulllowtp;

    private Short fdhalflowtp;

    private Short fdchildlowtp;

    private Short fdfreelowtp;

    private Short fdfullpeakqp;

    private Short fdhalfpeakqp;

    private Short fdchildpeakqp;

    private Short fdfreepeakqp;

    private Short fdfullpeaktp;

    private Short fdhalfpeaktp;

    private Short fdchildpeaktp;

    private Short fdfreepeaktp;

    private Short fdtranscoststp;
    
    /**
     * 景区名称
     */
    private String fsScenicname;
    
    /**
     * 景区代码
     */
    private String fsScenicno;
    
    /**
     * 票价类型（淡季-1 or 旺季-2 or 接送费用-3）
     */
    private String priceType;
    
    private List<TCCPrice> tccPrices;

    public String getFsNo() {
        return fsNo;
    }

    public void setFsNo(String fsNo) {
        this.fsNo = fsNo == null ? null : fsNo.trim();
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName == null ? null : fsName.trim();
    }

    public String getFsRegionno() {
        return fsRegionno;
    }

    public void setFsRegionno(String fsRegionno) {
        this.fsRegionno = fsRegionno == null ? null : fsRegionno.trim();
    }

    public String getFsAddr() {
        return fsAddr;
    }

    public void setFsAddr(String fsAddr) {
        this.fsAddr = fsAddr == null ? null : fsAddr.trim();
    }

    public String getFsType() {
        return fsType;
    }

    public void setFsType(String fsType) {
        this.fsType = fsType == null ? null : fsType.trim();
    }

    public String getFsDesc() {
        return fsDesc;
    }

    public void setFsDesc(String fsDesc) {
        this.fsDesc = fsDesc == null ? null : fsDesc.trim();
    }

    public String getFsLvl() {
        return fsLvl;
    }

    public void setFsLvl(String fsLvl) {
        this.fsLvl = fsLvl == null ? null : fsLvl.trim();
    }

    public String getFsOpentime() {
        return fsOpentime;
    }

    public void setFsOpentime(String fsOpentime) {
        this.fsOpentime = fsOpentime == null ? null : fsOpentime.trim();
    }

    public BigDecimal getFiStat() {
        return fiStat;
    }

    public void setFiStat(BigDecimal fiStat) {
        this.fiStat = fiStat;
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

    public String getFsResno() {
        return fsResno;
    }

    public void setFsResno(String fsResno) {
        this.fsResno = fsResno == null ? null : fsResno.trim();
    }

    public Short getFdfulllowqp() {
        return fdfulllowqp;
    }

    public void setFdfulllowqp(Short fdfulllowqp) {
        this.fdfulllowqp = fdfulllowqp;
    }

    public Short getFdhalflowqp() {
        return fdhalflowqp;
    }

    public void setFdhalflowqp(Short fdhalflowqp) {
        this.fdhalflowqp = fdhalflowqp;
    }

    public Short getFdchildlowqp() {
        return fdchildlowqp;
    }

    public void setFdchildlowqp(Short fdchildlowqp) {
        this.fdchildlowqp = fdchildlowqp;
    }

    public Short getFdfreelowqp() {
        return fdfreelowqp;
    }

    public void setFdfreelowqp(Short fdfreelowqp) {
        this.fdfreelowqp = fdfreelowqp;
    }

    public Short getFdfulllowtp() {
        return fdfulllowtp;
    }

    public void setFdfulllowtp(Short fdfulllowtp) {
        this.fdfulllowtp = fdfulllowtp;
    }

    public Short getFdhalflowtp() {
        return fdhalflowtp;
    }

    public void setFdhalflowtp(Short fdhalflowtp) {
        this.fdhalflowtp = fdhalflowtp;
    }

    public Short getFdchildlowtp() {
        return fdchildlowtp;
    }

    public void setFdchildlowtp(Short fdchildlowtp) {
        this.fdchildlowtp = fdchildlowtp;
    }

    public Short getFdfreelowtp() {
        return fdfreelowtp;
    }

    public void setFdfreelowtp(Short fdfreelowtp) {
        this.fdfreelowtp = fdfreelowtp;
    }

    public Short getFdfullpeakqp() {
        return fdfullpeakqp;
    }

    public void setFdfullpeakqp(Short fdfullpeakqp) {
        this.fdfullpeakqp = fdfullpeakqp;
    }

    public Short getFdhalfpeakqp() {
        return fdhalfpeakqp;
    }

    public void setFdhalfpeakqp(Short fdhalfpeakqp) {
        this.fdhalfpeakqp = fdhalfpeakqp;
    }

    public Short getFdchildpeakqp() {
        return fdchildpeakqp;
    }

    public void setFdchildpeakqp(Short fdchildpeakqp) {
        this.fdchildpeakqp = fdchildpeakqp;
    }

    public Short getFdfreepeakqp() {
        return fdfreepeakqp;
    }

    public void setFdfreepeakqp(Short fdfreepeakqp) {
        this.fdfreepeakqp = fdfreepeakqp;
    }

    public Short getFdfullpeaktp() {
        return fdfullpeaktp;
    }

    public void setFdfullpeaktp(Short fdfullpeaktp) {
        this.fdfullpeaktp = fdfullpeaktp;
    }

    public Short getFdhalfpeaktp() {
        return fdhalfpeaktp;
    }

    public void setFdhalfpeaktp(Short fdhalfpeaktp) {
        this.fdhalfpeaktp = fdhalfpeaktp;
    }

    public Short getFdchildpeaktp() {
        return fdchildpeaktp;
    }

    public void setFdchildpeaktp(Short fdchildpeaktp) {
        this.fdchildpeaktp = fdchildpeaktp;
    }

    public Short getFdfreepeaktp() {
        return fdfreepeaktp;
    }

    public void setFdfreepeaktp(Short fdfreepeaktp) {
        this.fdfreepeaktp = fdfreepeaktp;
    }

    public Short getFdtranscoststp() {
        return fdtranscoststp;
    }

    public void setFdtranscoststp(Short fdtranscoststp) {
        this.fdtranscoststp = fdtranscoststp;
    }

	public String getFsScenicname() {
		return fsScenicname;
	}

	public void setFsScenicname(String fsScenicname) {
		this.fsScenicname = fsScenicname;
	}

	public String getFsScenicno() {
		return fsScenicno;
	}

	public void setFsScenicno(String fsScenicno) {
		this.fsScenicno = fsScenicno;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public List<TCCPrice> getTccPrices() {
		return tccPrices;
	}

	public void setTccPrices(List<TCCPrice> tccPrices) {
		this.tccPrices = tccPrices;
	}
}