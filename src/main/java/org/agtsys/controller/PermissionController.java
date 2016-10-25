package org.agtsys.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.CheckFunction;
import org.agtsys.domain.Function;
import org.agtsys.domain.Permission;
import org.agtsys.domain.Role;
import org.agtsys.domain.User;
import org.agtsys.service.FunctionService;
import org.agtsys.service.PermissionService;
import org.agtsys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("permission/")
public class PermissionController extends BaseController{
	@Autowired private PermissionService permissonService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manage(Model model) throws Exception {
		List<Role> roles = roleService.selectRoles();
		model.addAttribute("roles", roles);
		return "permission_manage";
	}

	// 根据角色返回所有的功能列表，并且选中当前角色具有的功能
	@RequestMapping(value = "list/{roleid}", method = RequestMethod.POST)
	public @ResponseBody Object list(@PathVariable(value = "roleid") Long roleid)
			throws Exception {
		List<CheckFunction> all = functionService.getAllFunction();

		// 删除顶级菜单 parentId = 0
		Iterator<CheckFunction> ites = all.iterator();
		while (ites.hasNext()) {
			CheckFunction f = ites.next();
			if (f.getParentid() == 0) {
				ites.remove();
			}
		}
		Role role = new Role();
		role.setId(roleid);
		List<Function> permission = functionService.getFunctionByRole(role);
		for (Function f : permission) {
			for (int i = 0; i < all.size(); i++) {
				if (f.getId() == all.get(i).getId()) {
					all.get(i).setChecked(true);
				}
			}
		}
		return all;
	}
	
	//修改权限
	@ResponseBody
	@RequestMapping(value = "update",method=RequestMethod.POST)
	public Object functionUpdate(String flist, Permission pm,
			HttpSession session) throws Exception {
		pm.setId(null);
		User user = this.getSessionUser(session);
		pm.setCreatedby(user.getUsercode());
		pm.setCreationtime(new Date());
		pm.setIsstart(1);
		if(pm.getRoleid()==null){
			throw new Exception("删除角色权限时，roleid为null值！！");
		}
		permissonService.tx_delAddPremission(pm,flist);
		this.AddLogs(user, "用户修改角色ID："+pm.getRoleid()+" 的对应的功能ID为："+flist);
		return WebConstants.OPERATION_MESSAGE_SUCCESS;
	}
}
