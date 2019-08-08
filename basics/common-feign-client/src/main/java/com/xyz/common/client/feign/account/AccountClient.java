package com.xyz.common.client.feign.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-account")
public interface AccountClient {

    @RequestMapping("/debit")
    Boolean debit(@RequestParam("userId") String userId, @RequestParam("money")Integer money);
}
