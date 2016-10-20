package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.SystemConfigMapper;
import org.agtsys.domain.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemConfigServiceImpl implements SystemConfigService {
	@Autowired
	private SystemConfigMapper systemConfigMapper;
	@Override
	public List<SystemConfig> getSystemConfigByConfigType(
			SystemConfig systemConfig) throws Exception {
		return systemConfigMapper.selectSystemConfigByConfigType(systemConfig);
	}

	@Override
	public SystemConfig getSystemConfigById(SystemConfig systemConfig)
			throws Exception {
		return systemConfigMapper.selectSystemConfigById(systemConfig);
	}

	@Override
	public int insertSystemConfig(SystemConfig systemConfig) throws Exception {
		Integer max = systemConfigMapper.selectMaxTypeValueByConfigType(systemConfig);
		systemConfig.setConfigtypevalue(++max);
		return systemConfigMapper.insertSystemConfig(systemConfig);
	}

	@Override
	public Integer selectMaxTypeValueByConfigType(SystemConfig systemConfig)
			throws Exception {
		return systemConfigMapper.selectMaxTypeValueByConfigType(systemConfig);
	}

	@Override
	public Integer deleteSystemConfigById(SystemConfig systemConfig)
			throws Exception {
		return systemConfigMapper.deleteSystemConfigById(systemConfig);
	}

}
