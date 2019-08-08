package com.xyz.service.storage.dao;

import com.xyz.service.storage.entity.Storage;

public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    Storage findByCommodityCode(String code);
}