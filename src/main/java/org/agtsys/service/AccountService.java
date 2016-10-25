package org.agtsys.service;

import org.agtsys.domain.Account;
import org.agtsys.domain.AccountDetail;

public interface AccountService {
	int addAccount(Account account)throws Exception;
	Account selectAccountByUserId(Account account)throws Exception;
	int updateAccount(Account oldAccount)throws Exception;
	int tx_operationAccount(Account oldAccount, AccountDetail accountdetail)throws Exception;
}
