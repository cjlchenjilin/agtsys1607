package org.agtsys.service;

import org.agtsys.domain.User;

public interface UserService {
	User getUserByUser(User user);
	 int updateUser(User user) throws Exception;
}
