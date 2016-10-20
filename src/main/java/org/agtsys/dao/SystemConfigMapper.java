package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.SystemConfig;

public interface SystemConfigMapper {
	List<SystemConfig> selectSystemConfigByConfigType(SystemConfig systemConfig)throws Exception;
	SystemConfig selectSystemConfigById(SystemConfig systemConfig)throws Exception;
	int insertSystemConfig(SystemConfig systemConfig)throws Exception;
	Integer selectMaxTypeValueByConfigType(SystemConfig systemConfig)throws Exception;
	Integer deleteSystemConfigById(SystemConfig systemConfig)throws Exception;
}