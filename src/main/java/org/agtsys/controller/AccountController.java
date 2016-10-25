package org.agtsys.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.agtsys.constants.SystemConfigContstants;
import org.agtsys.constants.WebConstants;
import org.agtsys.domain.Account;
import org.agtsys.domain.AccountDetail;
import org.agtsys.domain.SystemConfig;
import org.agtsys.domain.User;
import org.agtsys.service.AccountService;
import org.agtsys.service.SystemConfigService;
import org.agtsys.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("account/")
public class AccountController extends BaseController{
	private static final Logger logger = Logger.getLogger(AccountController.class);
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired private UserService userService;
	@Autowired private AccountService accountService;
	
	@RequestMapping(value="manage",method=RequestMethod.GET)
	public String manage(Model model) throws Exception{
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setConfigtype(SystemConfigContstants.AccountConfigType);
		List<SystemConfig> accountTypeList =systemConfigService.getSystemConfigByConfigType(systemConfig);
		model.addAttribute("accountTypeList", accountTypeList);
		return "account_manage";
	}
	
	@RequestMapping(value="usercode/load",method=RequestMethod.GET)
	public @ResponseBody List<User> usercode_load(User user) throws Exception{
		return userService.getAllUser(user);
	}
	
	//财务操作
	@ResponseBody
	@RequestMapping("operation")
	public Object accountOperation(AccountDetail accountdetail,HttpSession session) throws Exception {
		// 定义一个账户用来查询
		Account account = new Account();
		account.setUserid(accountdetail.getUserid());
		// 查询该用户有没有账号
		Account oldAccount = accountService.selectAccountByUserId(account);
		if (null != oldAccount) {
			accountdetail.setDetaildatetime(new Date());
			if (accountService.tx_operationAccount(oldAccount, accountdetail)==1) {
				this.AddLogs(this.getSessionUser(session), accountdetail.getDetailtypename()+
						" : "+accountdetail.getMemo());
				return WebConstants.OPERATION_MESSAGE_SUCCESS;
			}else{
				return WebConstants.OPERATION_MESSAGE_FAIL;
			}
		}else{
			logger.error(accountdetail.getUserid()+"没有账号。");
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}
	
}
