package org.agtsys.controller;

import static org.agtsys.constants.WebConstants.CAPTCHCA_ERROR_MESSAGE;
import static org.agtsys.constants.WebConstants.OPERATION_MESSAGE_FAIL;
import static org.agtsys.constants.WebConstants.OPERATION_MESSAGE_SUCCESS;
import static org.agtsys.constants.WebConstants.SESSION_CAPTCHCA_KEY;
import static org.agtsys.constants.WebConstants.SESSION_LOGIN_KEY;
import static org.agtsys.constants.WebConstants.USER_LOGIN_ERROR;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.service.RoleService;
import org.agtsys.service.UserService;
import org.agtsys.util.MySqlPageTool;
import org.agtsys.validate.LoginValidateGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	// 使用日志
	private static Logger logger = Logger.getLogger(UserController.class);

	// 登录页面
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// 登录提交
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String dologin(String captchca,
			@Validated(value = { LoginValidateGroup.class }) User user,
			BindingResult results, HttpServletRequest request) {
		String cpatchca_session = (String) request.getSession().getAttribute(
				SESSION_CAPTCHCA_KEY);
		if (!cpatchca_session.equalsIgnoreCase(captchca)) {
			request.setAttribute("captchca_error", CAPTCHCA_ERROR_MESSAGE);
			return "login";
		}
		if (results.hasErrors()) {
			List<ObjectError> errors = results.getAllErrors();
			request.setAttribute("user_validate_errors", errors);
			return "login";
		} else {
			// 验证用户名和密码是否正确
			user = userService.getUserByUser(user);
			if (user != null) {
				// 保存用户到session
				request.getSession().setAttribute(SESSION_LOGIN_KEY, user);
				return "main";
			} else {
				request.setAttribute("user_login_errors", USER_LOGIN_ERROR);
				return "login";
			}
		}
	}

	// 系统退出
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 删除用户
		session.removeAttribute(SESSION_LOGIN_KEY);
		// 重定向到登录页面
		return "redirect:login";
	}

	// 返回修改密码页面
	@RequestMapping(value = "pwd/update", method = RequestMethod.GET)
	public String updatePwdPage() {
		return "update_pwd";
	}

	// 验证密码
	@RequestMapping(value = "pwd/check", method = RequestMethod.POST)
	public @ResponseBody String checkPwd(String pwd, HttpSession session) {
		User user = (User) session.getAttribute(SESSION_LOGIN_KEY);
		user.setUserpassword(pwd);
		// 验证用户名和密码是否正确
		if (userService.getUserByUser(user) != null) {
			return OPERATION_MESSAGE_SUCCESS;
		} else {
			return OPERATION_MESSAGE_FAIL;
		}
	}

	// 修改密码
	@RequestMapping(value = "pwd/update", method = RequestMethod.POST)
	public @ResponseBody String doUpdatePwd(String userpassword,
			HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SESSION_LOGIN_KEY);
		user.setLastupdatetime(new Date());
		user.setUserpassword(userpassword);
		if (userService.updateUser(user) == 1) {
			return OPERATION_MESSAGE_SUCCESS;
		} else {
			return OPERATION_MESSAGE_FAIL;
		}
	}

	// 返回用户管理页面
	@RequestMapping("manage")
	public String manage(Model model) throws Exception {
		List<Role> roles = roleService.selectRoles();
		if (logger.isDebugEnabled()) {
			logger.debug("----------加载角色列表: " + roles + "--------------");
		}
		model.addAttribute("roles", roles);
		return "user_manage";
	}

	// 返回用户管理页面
	@RequestMapping("list")
	public @ResponseBody Object list(User user, Integer page, Integer rows)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", userService.getCount(user));
		map.put("rows", userService.getPageUsersByUser(user, new MySqlPageTool(
				page, rows)));
		return map;
	}

	// 返回用户新增页面
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) throws Exception {
		List<Role> roles = roleService.selectRoles();
		model.addAttribute("roles", roles);
		return "user_add";
	}

	// 检查用户登录账号
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public @ResponseBody String checkUsercode(String usercode) throws Exception {
		User user = new User();
		user.setUsercode(usercode);
		if (userService.getUserByUser(user) == null) {
			return OPERATION_MESSAGE_SUCCESS;
		} else {
			return OPERATION_MESSAGE_FAIL;
		}
	}

	// 修改密码
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String doAdd(User user,HttpSession session) throws Exception {
		User session_user = (User) session.getAttribute(SESSION_LOGIN_KEY);
		user.setCreatedby(session_user.getUsercode());
		user.setCreationtime(new Date());
		if (userService.tx_addUser(user) == 1) {
			return OPERATION_MESSAGE_SUCCESS;
		} else {
			return OPERATION_MESSAGE_FAIL;
		}
	}

	/*// 开发用，上线请删除此方法
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(HttpSession session) {
		// 保存用户到session
		User user = new User();
		user.setId(2L);
		user.setRoleid(1L);
		user.setUsercode("admin");
		user.setUserpassword("2");
		user.setCreatedby("admin");
		session.setAttribute(SESSION_LOGIN_KEY, user);
		return "main";
	}*/
}
