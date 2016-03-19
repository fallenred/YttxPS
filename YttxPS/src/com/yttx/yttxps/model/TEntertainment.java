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

	private BigDecimal fdfulllowqp;

	private BigDecimal fdhalflowqp;

	private BigDecimal fdchildlowqp;

	private BigDecimal fdfreelowqp;

	private BigDecimal fdfulllowtp;

	private BigDecimal fdhalflowtp;

	private BigDecimal fdchildlowtp;

	private BigDecimal fdfreelowtp;

	private BigDecimal fdfullpeakqp;

	private BigDecimal fdhalfpeakqp;

	private BigDecimal fdchildpeakqp;

	private BigDecimal fdfreepeakqp;

	private BigDecimal fdfullpeaktp;

	private BigDecimal fdhalfpeaktp;

	private BigDecimal fdchildpeaktp;

	private BigDecimal fdfreepeaktp;

	private BigDecimal fdtranscoststp;

	/**
	 * 景区名称
	 */
	private String fsScenicname;

	/**
	 * 景区代码
	 */
	private String fsScenicno;

	/**
	 * 所属地区名称
	 */
	private String fsRegionName;

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

	public BigDecimal getFdfulllowqp() {
		return fdfulllowqp;
	}

	public void setFdfulllowqp(BigDecimal fdfulllowqp) {
		this.fdfulllowqp = fdfulllowqp;
	}

	public BigDecimal getFdhalflowqp() {
		return fdhalflowqp;
	}

	public void setFdhalflowqp(BigDecimal fdhalflowqp) {
		this.fdhalflowqp = fdhalflowqp;
	}

	public BigDecimal getFdchildlowqp() {
		return fdchildlowqp;
	}

	public void setFdchildlowqp(BigDecimal fdchildlowqp) {
		this.fdchildlowqp = fdchildlowqp;
	}

	public BigDecimal getFdfreelowqp() {
		return fdfreelowqp;
	}

	public void setFdfreelowqp(BigDecimal fdfreelowqp) {
		this.fdfreelowqp = fdfreelowqp;
	}

	public BigDecimal getFdfulllowtp() {
		return fdfulllowtp;
	}

	public void setFdfulllowtp(BigDecimal fdfulllowtp) {
		this.fdfulllowtp = fdfulllowtp;
	}

	public BigDecimal getFdhalflowtp() {
		return fdhalflowtp;
	}

	public void setFdhalflowtp(BigDecimal fdhalflowtp) {
		this.fdhalflowtp = fdhalflowtp;
	}

	public BigDecimal getFdchildlowtp() {
		return fdchildlowtp;
	}

	public void setFdchildlowtp(BigDecimal fdchildlowtp) {
		this.fdchildlowtp = fdchildlowtp;
	}

	public BigDecimal getFdfreelowtp() {
		return fdfreelowtp;
	}

	public void setFdfreelowtp(BigDecimal fdfreelowtp) {
		this.fdfreelowtp = fdfreelowtp;
	}

	public BigDecimal getFdfullpeakqp() {
		return fdfullpeakqp;
	}

	public void setFdfullpeakqp(BigDecimal fdfullpeakqp) {
		this.fdfullpeakqp = fdfullpeakqp;
	}

	public BigDecimal getFdhalfpeakqp() {
		return fdhalfpeakqp;
	}

	public void setFdhalfpeakqp(BigDecimal fdhalfpeakqp) {
		this.fdhalfpeakqp = fdhalfpeakqp;
	}

	public BigDecimal getFdchildpeakqp() {
		return fdchildpeakqp;
	}

	public void setFdchildpeakqp(BigDecimal fdchildpeakqp) {
		this.fdchildpeakqp = fdchildpeakqp;
	}

	public BigDecimal getFdfreepeakqp() {
		return fdfreepeakqp;
	}

	public void setFdfreepeakqp(BigDecimal fdfreepeakqp) {
		this.fdfreepeakqp = fdfreepeakqp;
	}

	public BigDecimal getFdfullpeaktp() {
		return fdfullpeaktp;
	}

	public void setFdfullpeaktp(BigDecimal fdfullpeaktp) {
		this.fdfullpeaktp = fdfullpeaktp;
	}

	public BigDecimal getFdhalfpeaktp() {
		return fdhalfpeaktp;
	}

	public void setFdhalfpeaktp(BigDecimal fdhalfpeaktp) {
		this.fdhalfpeaktp = fdhalfpeaktp;
	}

	public BigDecimal getFdchildpeaktp() {
		return fdchildpeaktp;
	}

	public void setFdchildpeaktp(BigDecimal fdchildpeaktp) {
		this.fdchildpeaktp = fdchildpeaktp;
	}

	public BigDecimal getFdfreepeaktp() {
		return fdfreepeaktp;
	}

	public void setFdfreepeaktp(BigDecimal fdfreepeaktp) {
		this.fdfreepeaktp = fdfreepeaktp;
	}

	public BigDecimal getFdtranscoststp() {
		return fdtranscoststp;
	}

	public void setFdtranscoststp(BigDecimal fdtranscoststp) {
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

	public String getFsRegionName() {
		return fsRegionName;
	}

	public void setFsRegionName(String fsRegionName) {
		this.fsRegionName = fsRegionName;
	}
}