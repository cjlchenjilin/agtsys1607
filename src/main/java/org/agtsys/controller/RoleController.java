package org.agtsys.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role/")
public class RoleController {
	@Autowired
	private RoleService roleService;

	// 返回角色列表页面
	@RequestMapping("manage")
	public String getListPage() {
		return "rolelist";
	}

	// 返回角色列表数据
	@RequestMapping("list")
	public @ResponseBody Object getRoles() throws Exception {
		return roleService.selectRoles();
	}

	// 返回添加角色表单
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "roleadd";
	}

	// 检查角色名称是否存在
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public @ResponseBody String check(Role role) throws Exception {
		if (roleService.selectRoleByRole(role) == null) {
			return WebConstants.OPERATION_MESSAGE_SUCCESS;
		} else {
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}

	//添加角色
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String doAdd(Role role,HttpSession session) throws Exception {
		User user = getSessionUser(session);
		//设置角色对象的属性值
		role.setCreatedby(user.getUsercode());
		role.setCreationtime(new Date());
		if(roleService.addRole(role)==1)
		{
			return WebConstants.OPERATION_MESSAGE_SUCCESS;		
		}else{
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}
	
	//获取session里的用户对象
	private User getSessionUser(HttpSession session){
		return (User) session.getAttribute(WebConstants.SESSION_LOGIN_KEY);
	}
}
