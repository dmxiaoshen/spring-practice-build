package com.xyz.common.client.feign.storage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-storage")
public interface StorageClient {

    @GetMapping("/deduct")
    Boolean deduct(@RequestParam("commodityCode") String commodityCode,
                @RequestParam("count") Integer count);
}
