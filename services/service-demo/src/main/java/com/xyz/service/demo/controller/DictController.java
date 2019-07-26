package com.xyz.service.demo.controller;

import com.github.pagehelper.PageInfo;
import com.xyz.common.base.entity.AbstractController;
import com.xyz.service.demo.entity.Dict;
import com.xyz.service.demo.query.DictPagination;
import com.xyz.service.demo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dict")
public class DictController extends AbstractController {

    @Autowired
    private DictService dictService;

    @PostMapping
    public String add(@RequestBody Dict dict){
        dictService.add(dict);
        return "success";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id")String id,@RequestBody Dict dict){
        dict.setId(id);
        dictService.update(dict);
        return "success";
    }

    @GetMapping("/{id}")
    public Dict get(@PathVariable("id")String id){
        return dictService.get(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")String id){
         dictService.delete(id);
         return "success";
    }

    @GetMapping("/page")
    public PageInfo<Dict> page(DictPagination dictPagination){
        return dictService.page(dictPagination);
    }

}
