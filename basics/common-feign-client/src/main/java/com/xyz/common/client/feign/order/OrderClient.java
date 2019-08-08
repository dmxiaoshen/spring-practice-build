package com.xyz.common.client.feign.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-order")
public interface OrderClient {

    @GetMapping("/create")
    Integer create(@RequestParam("userId") String userId, @RequestParam("commodityCode")String commodityCode, @RequestParam("count")Integer count);

}
