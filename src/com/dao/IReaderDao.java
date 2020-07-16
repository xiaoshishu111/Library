package com.dao;

import com.domain.Reader;
import vo.PageReaderSearch;

import java.sql.SQLException;
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
    //根据查询参数对象、每页数据条数、当前页，返回搜索到的读者数据集
    List<Reader> findReaders(PageReaderSearch pageReaderSearch,int currentCount,int currentPage) throws SQLException;
    //查询搜索后的读者数据总条数
    int countSearchReaders(PageReaderSearch pageReaderSearch) throws SQLException;
}
