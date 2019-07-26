package com.xyz.common.base.entity;

import com.github.pagehelper.PageHelper;

import java.io.Serializable;

public abstract class AbstractPagination<T extends AbstractPagination<T>> implements Serializable {

    protected int pageNum = 1;

    protected int pageSize = 10;

    public T enablePaging() {
        PageHelper.startPage(pageNum, pageSize);
        return (T) this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
