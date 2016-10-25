package org.agtsys.service;

import org.agtsys.dao.PermissionMapper;
import org.agtsys.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired private PermissionMapper permissionMapper;
	@Override
	public int deletePermissionByPermission(Permission permission)
			throws Exception {
		return permissionMapper.deletePermissionByPermission(permission);
	}

	@Override
	public int insertPermission(Permission permissioni) throws Exception {
		return permissionMapper.insertPermission(permissioni);
	}

	//先删除旧权限，再新增新的权限
	@Override
	public void tx_delAddPremission(Permission pm, String flist) throws Exception {
		permissionMapper.deletePermissionByPermission(pm);
		if(flist!=null && !flist.trim().equals("")){
			String[] ids = flist.split(",");
			for (int i = 0; i < ids.length; i++) {
				pm.setFunctionid(Long.valueOf(ids[i]));
				permissionMapper.insertPermission(pm);
			}
		}
	}
}
