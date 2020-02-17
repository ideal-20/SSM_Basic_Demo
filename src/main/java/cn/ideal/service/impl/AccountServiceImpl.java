package cn.ideal.service.impl;

import cn.ideal.dao.AccountDao;
import cn.ideal.domain.Account;
import cn.ideal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户");
        return accountDao.findAll();
    }

    public void addAccount(Account account) {

        System.out.println("业务层：添加账户");
        accountDao.addAccount(account);
    }
}
