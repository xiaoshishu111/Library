package com.dao;

import com.domain.Book;
import com.util.PageBean;
import vo.BookVO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface IBookDao {
    //增加某条数据
    void save(Book book) throws Exception;
    //删除某条数据
    void delete(String bookId) throws Exception;
    //修改某条数据
    void update(Book book) throws Exception;
    //查寻所有数据
    List<Book> findAll() throws Exception;
    //查询某条数据
    Book findById(String bookId) throws Exception;
    //根据查询条件返回一条分页对象
    PageBean<Book> findPageReader(BookVO bookVO) throws Exception;
}
