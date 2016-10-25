package org.agtsys.dao;

import org.agtsys.domain.Account;

public interface AccountMapper {
    int insertAccount(Account account)throws Exception;
    Account selectAccountByUserId(Account account)throws Exception;
    int updateAccount(Account oldAccount)throws Exception;
}