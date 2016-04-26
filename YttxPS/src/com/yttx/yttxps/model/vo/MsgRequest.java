package com.yttx.yttxps.model.vo;

import java.util.Map;

import com.yttx.yttxps.model.Message;
import com.yttx.yttxps.model.TOrderlistWithBLOBs;

public class MsgRequest extends JqGridRequest implements
		java.io.Serializable {

	/**
	 * modify by marongcai
	 * 订单查询新增条件需要，修改了使用的对象
	 * 2016-3-15
	 */
	private static final long serialVersionUID = 1L;

	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void copyMessage(Map<String, Object> map) {
		if (message != null) {
//			map.put("id", message.getId() == null ? "" : message.getId());
//			map.put("msgDate", message.getMsgDate() == null ? "" : message.getMsgDate());
			map.put("msgStat", message.getMsgStat() == null ? "" : message.getMsgStat());
			System.out.println(map.get("msgStat"));
//			map.put("msgText", message.getMsgText() == null ? "" : message.getMsgText());
//			map.put("recvId", message.getRecvid() == null ? "" : message.getRecvid());
//			map.put("sendId", message.getSendid() == null ? "" : message.getSendid());
//			map.put("sendType", message.getSendType() == null ? "" : message.getSendType());
//			map.put("subId", message.getSubid() == null ? "" : message.getSubid());
		}
	}
}