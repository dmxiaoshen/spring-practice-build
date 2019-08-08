package com.xyz.service.account.controller;

import com.xyz.service.account.service.AccountService;
import com.xyz.service.base.controller.AbstractServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController extends AbstractServiceController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/debit")
    public Boolean debit(String userId, Integer money) {
        accountService.debit(userId, money);
        return true;
    }
}
