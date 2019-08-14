package com.xyz.app.business.service;

import com.xyz.common.client.feign.account.AccountClient;
import com.xyz.common.client.feign.order.OrderClient;
import com.xyz.common.client.feign.storage.StorageClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessService {

    @Resource
    private StorageClient storageClient;
    @Resource
    private OrderClient orderClient;
    @Resource
    private AccountClient accountClient;

    /**
     * 减库存，下订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    @GlobalTransactional
    public void purchase(String userId, String commodityCode, int orderCount) {
        //减库存
        storageClient.deduct(commodityCode,orderCount);
        //生产订单
        Integer money = orderClient.create(userId,commodityCode,orderCount);
        //扣用户钱
        accountClient.debit(userId,money);
    }
}
