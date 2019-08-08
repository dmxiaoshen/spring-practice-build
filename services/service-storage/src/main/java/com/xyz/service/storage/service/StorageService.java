package com.xyz.service.storage.service;

import com.xyz.service.storage.dao.StorageMapper;
import com.xyz.service.storage.entity.Storage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Transactional
    public void deduct(String commodityCode, int count) {
        Storage storage = storageMapper.findByCommodityCode(commodityCode);
        storage.setCount(storage.getCount() - count);

        storageMapper.updateByPrimaryKeySelective(storage);
    }
}
