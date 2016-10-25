 package org.agtsys.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.Logs;
import org.agtsys.domain.User;
import org.agtsys.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	@Autowired protected LogsService logsService;
	
	protected void AddLogs(User user, String operation_msg) throws Exception{
		Logs logs = new Logs();
		logs.setUserid(user.getId());
		logs.setUsername(user.getUsercode());
		logs.setOperateinfo(operation_msg);
		logs.setOperatedatetime(new Date());
		logsService.insertLogs(logs);
	}
	
	protected User getSessionUser(HttpSession session){
		return (User)session.getAttribute(WebConstants.SESSION_LOGIN_KEY);
	}
}
