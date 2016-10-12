package org.agtsys.service;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public User getUserByUser(User user) {
		return userMapper.getUserByUser(user);
	}
}
