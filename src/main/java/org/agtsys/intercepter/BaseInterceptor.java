package org.agtsys.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseInterceptor implements HandlerInterceptor {
	
	@Override
	public abstract boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception;

	protected boolean filterLogin(HttpServletRequest request){
		String path = request.getServletPath(); // 截去上下文路径，例如：/user/login
		//过滤首页的链接
		if (path.equals("/user/login") || path.equals("/captchca/get") || path.equals("/captchca/check")) {
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
