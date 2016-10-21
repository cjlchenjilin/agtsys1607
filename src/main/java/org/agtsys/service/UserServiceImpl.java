package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.agtsys.util.MySqlPageTool;
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
	@Override
	public int updateUser(User user) throws Exception {
		return userMapper.updateUser(user);
	}
	@Override
	public List<User> getPageUsersByUser(User user, MySqlPageTool pt)
			throws Exception {
		return userMapper.getPageUsersByUser(user, pt);
	}
	@Override
	public Integer getCount(User user) throws Exception {
		return userMapper.getCount(user);
	}
}
