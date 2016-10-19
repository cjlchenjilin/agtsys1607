package org.agtsys.service;

import java.util.List;

import org.agtsys.domain.Role;

public interface RoleService {
	List<Role> selectRoles()throws Exception;
	Role selectRoleByRole(Role role)throws Exception;
	int addRole(Role role)throws Exception;
}
