package com.yttx.yttxps.model.corder;
/**
 * 类描述：游客：对应订单中的游客
 */
public class Tourist {

	//订单客户ID(批次号)
		private Long  orderId;
		
		//自动序号
		private Integer seq;
		
		//客户姓名
		private String name;
		
		//证件类型
		private String idType;
		
		//证件号
		private String id;
		
		//联系方式
		private String tel;
		
		//备注
		private String mark;
		
		//状态
		private Integer stat;

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public Integer getSeq() {
			return seq;
		}

		public void setSeq(Integer seq) {
			this.seq = seq;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIdType() {
			return idType;
		}

		public void setIdType(String idType) {
			this.idType = idType;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public Integer getStat() {
			return stat;
		}

		public void setStat(Integer stat) {
			this.stat = stat;
		}

		@Override
		public String toString() {
			return "Tourist [orderId=" + orderId + ", seq=" + seq + ", name=" + name + ", idType=" + idType + ", id="
					+ id + ", tel=" + tel + ", mark=" + mark + ", stat=" + stat + "]";
		}
}
