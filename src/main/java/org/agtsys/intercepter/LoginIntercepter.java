package org.agtsys.intercepter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.User;
import org.agtsys.exception.AccessDenyException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//过滤登录页面
		if(this.filterLogin(request)){
			return true;
		}
		//防止非法路径进入
		if (checkurl(request)) {
			return true;
		}
		//登录验证
		if(checkLogin(request,response)){
			return true;
		}
		return false;
	}

	// 拦截页面非法请求：盗链等等
	private boolean checkurl(HttpServletRequest request)
			throws AccessDenyException {
		String referer = request.getHeader("REFERER");// 获取父url
		//判断合法
		if ("".equals(referer) || null == referer) {
			throw new AccessDenyException("非法访问页面，你个混蛋！！");
		}else{
			if(referer.indexOf("/user/login")>0){
				return true;
			}
			else{
				return false;
			}
		}
	}

	// 验证登录方法
	private boolean checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 2、如果用户已经登录放行
		if (request.getSession().getAttribute(WebConstants.SESSION_LOGIN_KEY) != null) {
			User user = (User) request.getSession().getAttribute(
					WebConstants.SESSION_LOGIN_KEY);
			//return checkPermission(user, request);
			return true;
		}else{
			// 3、非法请求 即这些请求需要登录后才能访问
			// 重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
	}
}
