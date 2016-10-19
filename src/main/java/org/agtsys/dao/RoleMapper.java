package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.Role;

public interface RoleMapper {
	List<Role> selectRoles()throws Exception;
	Role selectRoleByRole(Role role)throws Exception;
	int addRole(Role role)throws Exception;
	int updateRole(Role role)throws Exception;
	int deleteRole(Role role )throws Exception;
}