package com.yttx.yttxps.model.vo;

import java.io.Serializable;
import java.util.Map;

import com.yttx.yttxps.model.TransportarrangePrice;

public class TransportarrangePriceRequest extends JqGridRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4373728187439975965L;
	
	private TransportarrangePrice transportarrangePrice;

	
	
	public TransportarrangePrice getTransportarrangePrice() {
		return transportarrangePrice;
	}



	public void setTransportarrangePrice(TransportarrangePrice transportarrangePrice) {
		this.transportarrangePrice = transportarrangePrice;
	}



	public void copyTransportarrangePrice(Map<String, Object> map) {
		if (transportarrangePrice != null) {
			map.put("fsResno", transportarrangePrice.getFsResno());   //车型线路(线路统称)资源ID
			map.put("fiGenindex", transportarrangePrice.getFiGenindex());   //线路统称自动序号
			map.put("tgenname", transportarrangePrice.getTgenname());   //线路统称名字
			map.put("fsTransNo", transportarrangePrice.getFsTransNo());   //车型代码
			map.put("fsCcno", transportarrangePrice.getFsCcno());
			map.put("fsCcname", transportarrangePrice.getFsCcname());
		}
	}
}
