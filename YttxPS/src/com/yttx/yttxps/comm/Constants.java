package com.yttx.yttxps.comm;

import java.math.BigDecimal;

public class Constants {

	public static final String SESSIONID = "sessionEntity";

	public static final String SYSCONFIG = "sysconfig";

	public static final String SYSMENUTREE = "sysmenutree";

	public static final String SYSMENULIST = "sysmenulist";

	public static final String SNAPSHOT_NAMESPACE = "http://www.yttx.com/";

	public static final String CODE_MASTER_LIST = "codeMasterList";

	public static final String CODE_MASTER_MAP = "codeMasterMap";

	public static final String CODE_MASTER_JSON = "codeMasterJson";
	
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径

	/**
	 * 订单状态
	 * @author huangtao
	 * add date 2016-03-16
	 *
	 */
	public enum OrderStat {
		/** 询价 */
		INQUIRY {public BigDecimal getVal(){return new BigDecimal(-10);}},
		/** 报价 */
		OFFER {public BigDecimal getVal(){return new BigDecimal(-5);}},
		/** 待审核 */
		WAITCONFIRM {public BigDecimal getVal(){return BigDecimal.ZERO;}},
		/** 已审核 */
		AUDITED {public BigDecimal getVal(){return BigDecimal.ONE;}},
		/** 已确认（弃用） */
		WAITPAYMENT {public BigDecimal getVal(){return new BigDecimal(2);}},
		/** 已付定金（弃用）  */
		PAYMENTDEPOSIT {public BigDecimal getVal(){return new BigDecimal(4);}},
		/** 转账  */
		TRANSFER {public BigDecimal getVal(){return new BigDecimal(6);}},
		/** 已付全款（可出团）*/
		PAYMENT {public BigDecimal getVal(){return new BigDecimal(8);}},
		/** 已完成 */
		END {public BigDecimal getVal(){return new BigDecimal(32);}};

		public abstract BigDecimal getVal();
	}
	
	/**
	 * 消息接收角色
	 */
	public enum RecRole {
		/** 忽略 */
		CANCEL {public int getVal(){return -1;}},
		/** 所有人 */
		ALL {public int getVal(){return 0;}},
		/** 主管 */
		DIRECTOR {public int getVal(){return 1;}},
		/** 操作员 */
		OPERATOR {public int getVal(){return 2;}};

		public abstract int getVal();
	}
	
	/**
	 * 消息模板
	 */
	public enum MsgTemp {
		/** 用户审核 */
		AUDIT {public String getVal(){return "1002";}},
		/** 报价 */
		DIRECTOR {public String getVal(){return "1002";}},
		/** 订制线路订单审核 */
		CUS_ORDER_AUDIT {public String getVal(){return "1002";}},
		/** 专家线路订单审核 */
		EXP_ORDER_AUDIT {public String getVal(){return "1002";}},
		/** 出团 */
		TOURS {public String getVal(){return "1002";}},
		/** 订单备注 */
		ORDER_REMARK {public String getVal(){return "1002";}},
		/** 结算单 */
		STATEMENT {public String getVal(){return "1002";}},
		/** 结算完毕 */
		STATEMENT_DONE {public String getVal(){return "1002";}};

		public abstract String getVal();
	}
}
