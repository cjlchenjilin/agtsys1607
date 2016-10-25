package org.agtsys.service;

import java.util.List;

import org.agtsys.domain.User;
import org.agtsys.util.MySqlPageTool;

public interface UserService {
	User getUserByUser(User user);

	int updateUser(User user) throws Exception;

	List<User> getPageUsersByUser(User user,
			MySqlPageTool pt) throws Exception;
	
	 Integer getCount(User user)throws Exception;
	 
	 public int tx_addUser(User user) throws Exception;
	 List<User> getAllUser(User user)throws Exception;
}
