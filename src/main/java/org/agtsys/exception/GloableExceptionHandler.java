package org.agtsys.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agtsys.constants.WebConstants;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GloableExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv = new ModelAndView();
		/*//判断是否是ajax请求
		if(request.getHeader("X-Requested-With")!=null){
			mv.setViewName("ajax_exception");
			mv.addObject("exception","服务器异常，请联系管理员！");
		}else{
			mv.setViewName("non_ajax_exception");
			mv.addObject("exception","non-ajax:服务器异常，请联系管理员！");
		}*/
		mv.setViewName("non_ajax_exception");
		if(ex instanceof AccessDenyException){
			mv.addObject("exception",ex.getMessage());
		}else{
			mv.addObject("exception","non-ajax:服务器异常，请联系管理员！");
		}
		return mv;
	}
}
