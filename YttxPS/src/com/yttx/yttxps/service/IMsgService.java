package com.yttx.yttxps.service;

import java.util.List;
import java.util.Map;

import com.yttx.yttxps.model.Message;



/**
 * 
 * 消息提醒service接口
 * @author huangtao
 *
 */
public interface IMsgService {
	
	/**
	 * 保存需要推送的消息
	 * @param obj
	 */
	void saveMsg(Object obj, String sendid);

	List<Message> selectNewMsg(Map<String, Object> map);

	List<Message> selectSelectivePage(Map<String, Object> map);
	
	boolean readMsg(String id);

	void delete(String id);
}
