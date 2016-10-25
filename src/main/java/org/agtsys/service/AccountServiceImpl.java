package org.agtsys.service;

import org.agtsys.dao.AccountDetailMapper;
import org.agtsys.dao.AccountMapper;
import org.agtsys.domain.Account;
import org.agtsys.domain.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired private AccountDetailMapper accountDetailMapper; 

	@Override
	public int addAccount(Account account) throws Exception {
		return accountMapper.insertAccount(account);
	}

	@Override
	public Account selectAccountByUserId(Account account) throws Exception {
		return accountMapper.selectAccountByUserId(account);
	}

	// 事务操作
	@Override
	public int tx_operationAccount(Account oldAccount,
			AccountDetail accountdetail) throws Exception {
		// 资金计算
		oldAccount.setMoney(oldAccount.getMoney() + accountdetail.getMoney());
		oldAccount.setMoneybak(oldAccount.getMoneybak()
				+ accountdetail.getMoney());
		accountdetail.setAccountmoney(oldAccount.getMoney());
		// 修改账户资金
		accountMapper.updateAccount(oldAccount);
		// 记录流水
		return accountDetailMapper.insertAccountDetail(accountdetail);
	}

	@Override
	public int updateAccount(Account oldAccount) throws Exception {
		return accountMapper.updateAccount(oldAccount);
	}
}
