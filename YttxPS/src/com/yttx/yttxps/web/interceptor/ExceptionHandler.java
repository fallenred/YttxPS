package com.yttx.yttxps.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yttx.except.AuthorityException;
import com.yttx.except.AuthorityPwdException;
import com.yttx.except.BusinessException;
import com.yttx.except.SessionException;
import com.yttx.except.SystemException;
import com.yttx.yttxps.web.action.BaseController;



/**
 * 异常集中处理
 * 
 * @author Administrator
 * 
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory
			.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		if (e instanceof BusinessException) {
			BaseController controller = (BaseController) obj;
			String input = controller.getInput();
			return handle(request, response, e, input, controller);
		} else if (e instanceof SessionException) {
			return handle(request, response, e, "comm/session");
		} else if (e instanceof AuthorityException) {
			return handle(request, response, e, "comm/authority");
		} else if (e instanceof AuthorityPwdException) {
			return new ModelAndView(new RedirectView("/user/pwd/initupd.do"));
		} else if (e instanceof SystemException) {
			return handle(request, response, e, "comm/sys");
		} else {
			logger.error("未知异常", e);
			return handle(request, response, e, "comm/sys");
		}
	}

	private ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Exception e, String view) {
		return handle(request, response, e, view, null);
	}

	/**
	 * 异常处理
	 * 
	 * @param request
	 * @param response
	 * @param e
	 * @param view
	 * @return
	 */
	private ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Exception e, String view,
			BaseController controller) {

		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 页面跳转
			Map<String, Object> model = new HashMap<String, Object>();
			Enumeration<?> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String key = (String) enu.nextElement();
				model.put(key, request.getParameter(key));
			}
			if(controller!= null){
				Map<String, Object> errorModel = controller.getModelMap();
				if(errorModel != null)
					model.putAll(errorModel);
			}
			if ("common/sys".equals(view)) {
				StringBuilder message = new StringBuilder();
				message.append(e.toString() + "<br>");
				StackTraceElement[] element = e.getStackTrace();
				for (StackTraceElement row : element)
					message.append(row.toString() + "<br>");
				model.put("error", message.toString());
			} else
				model.put("error", e.getMessage());
			
			return new ModelAndView(view, model);
		} else {
			// json处理
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				if ("comm/sys".equals(view)) {
					StringBuilder message = new StringBuilder();
					message.append(e.toString() + "\n");
					StackTraceElement[] element = e.getStackTrace();
					for (StackTraceElement row : element)
						message.append(row.toString() + "\n");
					writer.write(message.toString());
				} else
					writer.write(e.getMessage().replaceAll("<br>", "\n"));
				writer.flush();
			} catch (IOException e1) {
			}
			return null;
		}
	}
}
