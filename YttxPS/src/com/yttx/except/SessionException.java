package com.yttx.except;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:session异常类
 * 
 * @author kereny
 */
public class SessionException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(SessionException.class);

	private static final String key = "会话超时";

	public SessionException() {
		super(key);
		logger.info(key);
	}

	public SessionException(Object source) {
		super(key);
		logger.info(key, source);
	}

	public SessionException(Object source, Exception e) {
		super(key);
		logger.info(key,source, e);
	}

	public SessionException(String key) {
		super(key);
		logger.info(key);
	}

	public SessionException(Object source, String key) {
		super(key);
		logger.info(key, source);
	}

	public SessionException(Object source, String key, Exception e) {
		super(key);
		logger.info(key, source, e);
	}

}
