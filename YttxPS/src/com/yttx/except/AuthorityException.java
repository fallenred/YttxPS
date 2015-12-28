package com.yttx.except;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:权限异常类
 * 
 * @author kereny
 */
public class AuthorityException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(AuthorityException.class);

	private static final String key = "无效权限";

	public AuthorityException() {
		super(key);
		logger.info(key);
	}

	public AuthorityException(Object source) {
		super(key);
		logger.info(key, source);
	}

	public AuthorityException(Object source, Exception e) {
		super(key);
		logger.info(key, source, e);
	}

	public AuthorityException(String key) {
		super(key);
		logger.info(key);
	}

	public AuthorityException(Object source, String key) {
		super(key);
		logger.info(key, source);
	}

	public AuthorityException(Object source, String key, Exception e) {
		super(key);
		logger.info(key, source, e);
	}

}
