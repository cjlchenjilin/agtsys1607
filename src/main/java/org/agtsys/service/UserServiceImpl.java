package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.AccountMapper;
import org.agtsys.dao.UserMapper;
import org.agtsys.domain.Account;
import org.agtsys.domain.User;
import org.agtsys.util.MySqlPageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;
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
	
	//新增用户，同时为该用户添加资金账户,需使用事物管理
	public int tx_addUser(User user) throws Exception{
		userMapper.insertUser(user);
		Account account = new Account();
		account.setMoney(0.0);
		account.setMoneybak(0.0);
		account.setUserid(user.getId());
		return accountMapper.insertAccount(account);
	}
	@Override
	public List<User> getAllUser(User user) throws Exception {
		return userMapper.getAllUser(user);
	}
}
