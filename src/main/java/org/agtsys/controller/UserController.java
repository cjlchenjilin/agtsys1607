package org.agtsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import static  org.agtsys.constants.WebConstants.*;

import org.agtsys.domain.User;
import org.agtsys.service.UserService;
import org.agtsys.validate.LoginValidateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String dologin(String captchca,@Validated(value={LoginValidateGroup.class}) User user,
			BindingResult results,HttpServletRequest request){
		String cpatchca_session = (String)request.getSession().getAttribute(SESSION_CAPTCHCA_KEY);
		if(!cpatchca_session.equalsIgnoreCase(captchca)){
			request.setAttribute("captchca_error", CAPTCHCA_ERROR_MESSAGE);
			return "login";
		}
		if(results.hasErrors()){
			List<ObjectError> errors= results.getAllErrors();
			request.setAttribute("user_validate_errors", errors);
			return "login";
		}else{
			//验证用户名和密码是否正确
			user = userService.getUserByUser(user);
			if(user!=null){
				return "main";
			}else{
				request.setAttribute("user_login_errors", USER_LOGIN_ERROR);
				return "login";
			}			
		}	
	}
}
