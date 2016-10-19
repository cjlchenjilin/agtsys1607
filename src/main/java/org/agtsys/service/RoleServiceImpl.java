package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.RoleMapper;
import org.agtsys.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> selectRoles() throws Exception {
		return roleMapper.selectRoles();
	}
	@Override
	public Role selectRoleByRole(Role role) throws Exception {
		return roleMapper.selectRoleByRole(role);
	}
	@Override
	public int addRole(Role role) throws Exception {
		return roleMapper.addRole(role);
	}
}
