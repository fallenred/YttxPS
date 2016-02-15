package com.yttx.yttxps.web.action.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yttx.comm.StringUtil;
import com.yttx.yttxps.comm.Constants;
import com.yttx.yttxps.model.SessionEntity;
import com.yttx.yttxps.model.SysOper;
import com.yttx.yttxps.service.ISysService;
import com.yttx.yttxps.web.action.BaseController;
import com.yttx.yttxps.web.action.LoginController;

@Controller
@Scope("prototype")
@RequestMapping("user/")
public class UserController extends BaseController {

	
	
static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ISysService sysService;
	

	/**
	 * 用户修改密码
	 * 
	 * @param origpwd
	 * @param targpwd
	 * @param confpwd
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "password.htm", method = RequestMethod.POST)
	public String password(
			@RequestParam(value = "origpwd") String origpwd,
			@RequestParam(value = "targpwd") String targpwd, 
			@RequestParam(value = "confpwd") String confpwd,
			@RequestParam(value = "forceflag",  required = false) String forceflag,
			
			Model model) {
		
		String url = "user/password";
		
		if(!StringUtil.nullOrBlank(forceflag) && "1".equalsIgnoreCase(forceflag)){
			url = "user/init";
		}
		
		logger.debug("{} {} {}", origpwd+targpwd+confpwd);
		
		HttpSession session = request.getSession();
		SessionEntity sessionEntity = (SessionEntity)session.getAttribute(Constants.SESSIONID);
	
		logger.debug("当前登录ID{} ", sessionEntity.getId());
		
		SysOper sysOper = sysService.findOperById(sessionEntity.getId().trim());
		if(sysOper == null){
			model.addAttribute("message", "系统登录错误,请重新登录");
		}else{
			if(!sysOper.getSysOperPwd().equalsIgnoreCase(origpwd)){
				model.addAttribute("message", "用户原密码错误");
			}else{
				sysOper.setSysOperPwd(confpwd);
				sysOper.setPwdStat(1);
				
				int rs = sysService.updateSysOper(sysOper);
				if(rs != 1){
					model.addAttribute("message", "密码修改失败,请重试");
				}else{
					sessionEntity.setPwdStatus(1);
					session.removeAttribute(Constants.SESSIONID);
					session.setAttribute(Constants.SESSIONID, sessionEntity);
					model.addAttribute("message", "密码修改成功");
					model.addAttribute("succflag", "1");
				}
			}
		}
		return url;
	}

}
