package com.xyz.service.account.service;

import com.xyz.service.account.dao.AccountMapper;
import com.xyz.service.account.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountService {

    private static final String ERROR_USER_ID = "1002";
    @Resource
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public void debit(String userId, Integer num) {
        Account account = accountMapper.selectByUserId(userId);
        account.setMoney(account.getMoney() - num);
        accountMapper.updateByPrimaryKeySelective(account);

        if (ERROR_USER_ID.equals(userId.toString())) {
            throw new RuntimeException("account branch exception");
        }
    }
}
