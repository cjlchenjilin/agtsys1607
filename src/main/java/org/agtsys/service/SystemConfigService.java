package org.agtsys.service;

import java.util.List;

import org.agtsys.domain.SystemConfig;

public interface SystemConfigService {
	List<SystemConfig> getSystemConfigByConfigType(SystemConfig systemConfig)throws Exception;
	SystemConfig getSystemConfigById(SystemConfig systemConfig)throws Exception;
	int insertSystemConfig(SystemConfig systemConfig)throws Exception;
	Integer selectMaxTypeValueByConfigType(SystemConfig systemConfig)throws Exception;
	Integer deleteSystemConfigById(SystemConfig systemConfig)throws Exception;
}
