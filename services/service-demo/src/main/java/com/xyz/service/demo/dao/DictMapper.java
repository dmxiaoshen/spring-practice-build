package com.xyz.service.demo.dao;

import com.xyz.service.demo.entity.Dict;
import com.xyz.service.demo.query.DictPagination;

import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    int deleteLogic(String id);

    List<Dict> page(DictPagination dictPagination);
}