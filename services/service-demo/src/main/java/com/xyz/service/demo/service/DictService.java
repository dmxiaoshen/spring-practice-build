package com.xyz.service.demo.service;

import com.github.pagehelper.PageInfo;
import com.xyz.service.demo.dao.DictMapper;
import com.xyz.service.demo.entity.Dict;
import com.xyz.service.demo.query.DictPagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DictService {

    @Resource
    private DictMapper dictMapper;


    @Transactional
    public void add(Dict dict) {
        dict.setId(UUID.randomUUID().toString().replaceAll("-",""));
        dictMapper.insertSelective(dict);
    }

    @Transactional
    public void update(Dict dict) {
        dictMapper.updateByPrimaryKeySelective(dict);
    }

    public Dict get(String id) {
        return dictMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void delete(String id) {
        dictMapper.deleteLogic(id);
    }

    public PageInfo<Dict> page(DictPagination dictPagination) {
        List<Dict> result = dictMapper.page(dictPagination.enablePaging());
        return new PageInfo<>(result);
    }
}
