package com.service;

import com.domain.Lend;

import java.util.List;

public interface ILendService {

    //查询所有借阅记录
    List<Lend> findAllLends() throws Exception;
    //添加借阅记录
    void saveLend(Lend lend) throws Exception;
    //更新查阅记录
    void updateLend(Lend lend) throws Exception;
}
