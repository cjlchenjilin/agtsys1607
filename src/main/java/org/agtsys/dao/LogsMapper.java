package org.agtsys.dao;

import org.agtsys.domain.Logs;

public interface LogsMapper {
	int insertLogs(Logs logs)throws Exception;
}