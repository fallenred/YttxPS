package com.yttx.yttxps.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.yttx.comm.StringUtil;
import com.yttx.except.AuthorityException;
import com.yttx.except.AuthorityPwdException;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.Menu;
import com.yttx.yttxps.model.SessionEntity;



/**
 * 用户权限验证
 * 
 * @author kereny
 * 
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private String[] excludeMapping;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length() + 1);
		
		if(StringUtil.nullOrBlank(uri)) 
			return super.preHandle(request, response, handler);
		
		boolean excludeMappingFlg = false;
		if (excludeMapping != null)
			for (String row : excludeMapping)
				if (uri.indexOf(row.trim()) != -1) {
					excludeMappingFlg = true;
					break;
				}
		if (excludeMappingFlg)
			//不做权限认证
			return super.preHandle(request, response, handler);
		else {
			
			//验证规则,系统配置为URL功能但客户权限中不存在的为不可访问
			SessionEntity sessionEntity = (SessionEntity) request.getSession().getAttribute(Constants.SESSIONID);
			Map<String,Menu> customRejectMap = sessionEntity.getCustomRejectMap();
			
			if(customRejectMap.containsKey(uri)){
				throw new AuthorityException();
			}
			
			//密码未修改画面
			long pwdStatus = sessionEntity.getPwdStatus();
			if (pwdStatus == 0 && !uri.startsWith("user/password"))
				throw new AuthorityPwdException();
			return super.preHandle(request, response, handler);
		}
	}
	

	public void setExcludeMapping(String excludeMapping) {
		String[] temp = excludeMapping.split(",");
		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].replaceAll("\\*", "");
		this.excludeMapping = temp;
	}
}
