package com.yttx.yttxps.model.corder;
/**
 * 类描述：旅客信息表：对应每一批次中具体的每一位旅客
 * @author sunchao
 * @date 2016年2月23日 下午3:49:22
 */
public class Customer {
	//订单客户ID(批次号)
	private Long  customId;
	
	//自动序号
	private Integer cusSeq;
	
	//客户姓名
	private String name;
	
	//证件类型
	private String certIdType;
	
	//证件号
	private String certId;
	
	//联系方式
	private String tel;
	
	//备注
	private String mark;
	
	//状态
	private Integer cusStat;

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public Integer getCusSeq() {
		return cusSeq;
	}

	public void setCusSeq(Integer cusSeq) {
		this.cusSeq = cusSeq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertIdType() {
		return certIdType;
	}

	public void setCertIdType(String certIdType) {
		this.certIdType = certIdType;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getCusStat() {
		return cusStat;
	}

	public void setCusStat(Integer cusStat) {
		this.cusStat = cusStat;
	}
}
