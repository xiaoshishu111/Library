package com.util;

import com.domain.Book;

import java.util.List;

public class PageBean<T> {
    //当前页
    private int currentPage;
    //当前条数
    private int currentCount;
    //总页数
    private int totalPage;
    //总数据条数
    private int totalCount;
    //当前数据
    private List<T> beans;

    public PageBean(int currentPage, int currentCount, int totalPage, int totalCount, List<T> beans) {
        this.currentPage = currentPage;
        this.currentCount = currentCount;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.beans = beans;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getBeans() {
        return beans;
    }

    public void setBeans(List<T> beans) {
        this.beans = beans;
    }
}
