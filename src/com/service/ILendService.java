package com.service;

import com.domain.Lend;

import java.util.List;

public interface ILendService {

    //查询所有借阅记录
    List<Lend> findAllLends() throws Exception;
}
