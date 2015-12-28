package com.yttx.except;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:业务异常类
 * 
 * @author kereny
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(BusinessException.class);

	private static final String key = "业务异常";

	public BusinessException() {
		super(key);
		logger.info(key);
	}

	public BusinessException(Object source) {
		super(key);
		logger.info(key, source);
	}

	public BusinessException(Object source, Exception e) {
		super(key);
		logger.info(key, source, e);
	}

	public BusinessException(String key) {
		super(key);
		logger.info(key);
	}

	public BusinessException(Object source, String key) {
		super(key);
		logger.info(key, source);
	}

	public BusinessException(Object source, String key, Exception e) {
		super(key);
		logger.info(key, source, e);
	}

}
