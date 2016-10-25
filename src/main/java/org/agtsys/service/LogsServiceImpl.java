package org.agtsys.service;

import org.agtsys.dao.LogsMapper;
import org.agtsys.domain.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LogsServiceImpl implements LogsService {
	@Autowired private LogsMapper logsMapper;
	@Override
	public int insertLogs(Logs logs) throws Exception {
		return logsMapper.insertLogs(logs);
	}

}
