package org.agtsys.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.Function;
import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.exception.AccessDenyException;
import org.agtsys.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionInterceptor extends BaseInterceptor {
	@Autowired
	private FunctionService functionService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(this.filterLogin(request)){
			return true;
		}
		return checkPermission(request);
	}
	
	//验证用户权限
	private boolean checkPermission(HttpServletRequest request) throws Exception {
		boolean flag = false;
		String path = request.getServletPath(); //"/user/login"
		//截取模块名称
		String[] urls = path.split("/");
		String model = urls[1];
		
		if(path.contains("main/tree")||path.contains("check")){
			return true;
		}
		// 获取当前用户的权限功能
		User user = (User)request.getSession().getAttribute(WebConstants.SESSION_LOGIN_KEY);
		Role role = new Role();
		role.setId(user.getRoleid());
		List<Function> functionList = functionService.getFunctionByRole(role);
		for(Function function : functionList){
			if(function.getFuncurl().contains(model) && function.getFuncurl()!=null
					&& !function.getFuncurl().trim().equals("")){
				return flag=true;
			}
		}
		
		if(!flag){
			throw new AccessDenyException("对不起！！您没有权限访问该页面！！");
		}
		return flag;
	}
}
