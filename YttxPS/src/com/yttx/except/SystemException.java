package com.yttx.except;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:系统异常类
 * 
 * @author kereny
 */
public class SystemException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory
			.getLogger(SystemException.class);

	private static final String key = "系统错误";

	public SystemException() {
		super(key);
		logger.error(key);
	}

	public SystemException(Object source) {
		super(key);
		logger.error(key, source);
	}

	public SystemException(Object source, Exception e) {
		super(key);
		logger.error(key, source, e);
	}

	public SystemException(String key) {
		super(key);
		logger.error(key);
	}

	public SystemException(Object source, String key) {
		super(key);
		logger.error(key, source);
	}

	public SystemException(Object source, String key, Exception e) {
		super(key);
		logger.error(key, source, e);
	}

}
