package com.dao;

import com.domain.Book;
import com.domain.Reader;

import java.util.List;

public interface IReaderDao {
    //增加某条数据
    void save(Reader reader) throws Exception;
    //删除某条数据
    void delete(String readerId) throws Exception;
    //修改某条数据
    void update(Reader reader) throws Exception;
    //查寻所有数据
    List<Reader> findAll() throws Exception;
    //根据id查询某条数据
    Reader findById(String readerId) throws Exception;
    //根据账号查询数据
    Reader findByAccount(String readerAccount) throws Exception;
}
