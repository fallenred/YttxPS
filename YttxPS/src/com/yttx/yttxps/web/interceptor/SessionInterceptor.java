package com.yttx.yttxps.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.yttx.comm.StringUtil;
import com.yttx.except.SessionException;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.web.action.BaseController;



/**
 * 登录session验证
 * @author kereny
 * 
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	private String[] excludeMapping;
	
	static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		SessionEntity sessionEntity = (SessionEntity) request.getSession().getAttribute(
				Constants.SESSIONID);
		// session验证
		String requesturi = request.getRequestURI();
		String uri = requesturi.substring(request.getContextPath().length() + 1);
		
		logger.debug("访问URI地址{} {}", uri, requesturi);
		
		if (sessionEntity == null && !StringUtil.nullOrBlank(uri)) {
			boolean checkFlg = false;
			
			logger.debug("mapping lenght: {}", excludeMapping.length);
			
			if (excludeMapping != null)
				for (String row : excludeMapping){
					logger.debug("row: {}", row);
					if (uri.indexOf(row.trim()) != -1) {
						checkFlg = true;
						break;
					}
				}
			if (!checkFlg)
				throw new SessionException();
		}

		// 设置基本数据
		if (handler instanceof BaseController) {
			BaseController controller = (BaseController) handler;
			controller.setRequest(request);
			if (sessionEntity != null){
				controller.setSessionEntity(sessionEntity);
				String lastId =request.getParameter("lastId");
				if(!StringUtil.nullOrBlank(lastId))
					sessionEntity.setLastId(lastId);
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	public void setExcludeMapping(String excludeMapping) {
		String[] temp = excludeMapping.split(",");
		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].replaceAll("\\*", "");
		this.excludeMapping = temp;
	}

}