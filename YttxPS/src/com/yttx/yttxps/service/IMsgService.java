package com.yttx.yttxps.service;



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
	void saveMsg(Object obj);
}
