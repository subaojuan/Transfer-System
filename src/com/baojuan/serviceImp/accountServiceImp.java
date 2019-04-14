package com.baojuan.serviceImp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.baojuan.pojo.Account;
import com.baojuan.pojo.Log;
import com.baojuan.service.accountService;

public class accountServiceImp implements accountService {

	@Override
	public int transFer(Account accIn, Account accOut) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		// 判断账号和密码是否匹配
		Account accOutSelect = session.selectOne("com.baojuan.mapper.AccountMapper.selByaccnoPwd", accOut);
		if (accOutSelect != null) {
			if (accOutSelect.getBalance() >= accOut.getBalance()) {
				Account accInSelect = session.selectOne("com.baojuan.mapper.AccountMapper.selByaccnoName", accIn);
				if (accInSelect != null) {
					accIn.setBalance(accOut.getBalance());
					accOut.setBalance(-accOut.getBalance());
					int index = session.update("com.baojuan.mapper.AccountMapper.updateBalanceByAccno", accOut);
					index += session.update("com.baojuan.mapper.AccountMapper.updateBalanceByAccno", accIn);
					if (index == 2) {
						//日志表记录
						Log log=new Log();
						log.setAccOut(accOut.getAccno());
						log.setAccIn(accIn.getAccno());
						log.setMoney(accIn.getBalance());
						session.insert("com.baojuan.mapper.LogMapper.insertLog",log);
						//日志文件记录
						Logger logger=Logger.getLogger(accountServiceImp.class);
						logger.info(log.getAccOut()+"给"+log.getAccIn()+"在"+new Date().toString()+"转了"+log.getMoney());
						session.commit();
						session.close();
						return SUCCESS;
					} else {
						session.rollback();
						session.close();
						return ERROR;
					}
				} else {
					return ACCOUNT_NAME_NOT_ENOUGH;
				}
			} else {
				return ACCOUNT_BALANCE_NOT_ENOUGH;
			}
		} else {
			return ACCOUNT_PASSWORD_NOT_MATCH;
		}
	}
}
