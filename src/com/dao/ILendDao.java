package com.dao;

import com.domain.Lend;
import com.domain.Reader;

import java.util.List;

public interface ILendDao {
    //增加某条数据
    void save(Lend lend) throws Exception;
    //查寻所有数据
    List<Lend> findAll() throws Exception;
    //根据书的ID查询单条数据
    Lend findByBookId(String bookId) throws Exception;
    //更新借阅记录
    void update(Lend lend) throws Exception;
}
