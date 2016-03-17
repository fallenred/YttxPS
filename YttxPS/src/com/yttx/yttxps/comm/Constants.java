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
		/** 已确认（待付款） */
		WAITPAYMENT {public BigDecimal getVal(){return new BigDecimal(2);}},
		/** 已付定金  */
		PAYMENTdeposit {public BigDecimal getVal(){return new BigDecimal(4);}},
		/** 已付全款（可出团）*/
		PAYMENT {public BigDecimal getVal(){return new BigDecimal(8);}},
		/** 已完成 */
		END {public BigDecimal getVal(){return new BigDecimal(32);}};

		public abstract BigDecimal getVal();
	}
}
