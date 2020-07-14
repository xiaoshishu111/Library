package com.service;

import com.domain.Book;
import com.domain.Reader;

import java.util.List;

public interface IReaderService {

    //添加读者
    void saveReader(Reader reader) throws Exception;
    //删除读者
    void deleteReader(String readerId) throws Exception;
    //更新读者信息
    void updateReader(Reader reader) throws Exception;
    //查询所有读者信息
    List<Reader> findAllReaders() throws Exception;
    //查询单个读者的信息
    Reader findReader(String readerId) throws Exception;
    //根据账号查询读者信息
    Reader findReaderByAccount(String readerAccount) throws Exception;
}
