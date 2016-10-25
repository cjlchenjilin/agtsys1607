package org.agtsys.service;

import org.agtsys.domain.Permission;

public interface PermissionService {
	int deletePermissionByPermission(Permission permission) throws Exception;

	int insertPermission(Permission permissioni) throws Exception;

	void tx_delAddPremission(Permission pm, String flist) throws Exception;
}
