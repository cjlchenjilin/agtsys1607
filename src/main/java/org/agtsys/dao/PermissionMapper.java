package org.agtsys.dao;

import org.agtsys.domain.Permission;

public interface PermissionMapper {
   int deletePermissionByPermission(Permission permission)throws Exception;
   int insertPermission(Permission permissioni)throws Exception;
}