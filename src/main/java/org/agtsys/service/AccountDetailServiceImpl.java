package org.agtsys.service;

import org.agtsys.dao.AccountDetailMapper;
import org.agtsys.domain.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountDetailServiceImpl implements AccountDetailService {
	@Autowired
	private AccountDetailMapper accountdetailmapper;
	@Override
	public int insertAccountDetail(AccountDetail accountdetail)
			throws Exception {
		return accountdetailmapper.insertAccountDetail(accountdetail);
	}

}
