package org.agtsys.service;

import org.agtsys.domain.Logs;

public interface LogsService {
	int insertLogs(Logs logs)throws Exception;
}
