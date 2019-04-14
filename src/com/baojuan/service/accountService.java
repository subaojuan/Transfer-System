package com.baojuan.service;

import java.io.IOException;

import com.baojuan.pojo.Account;

public interface accountService {
	/**
	 *账号和密码不匹配状态码
	 */
	int ACCOUNT_PASSWORD_NOT_MATCH=1;
	//余额不足
	int ACCOUNT_BALANCE_NOT_ENOUGH=2;
	//账号和用户名不匹配
	int ACCOUNT_NAME_NOT_ENOUGH=3;
	//转账失败
	int ERROR=4;
	//转账成功
	int SUCCESS=5;
	/**
	 * 
	 * @param accIn   收入账户
	 * @param accOut  支出账户
	 * @return
	 * @throws IOException 
	 */
int transFer(Account accIn,Account accOut) throws IOException;
}
