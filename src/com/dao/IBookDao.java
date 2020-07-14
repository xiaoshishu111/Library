package com.dao;

import com.domain.Book;

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
    //模糊查询
    List<Book> searchBooks(Book book,BigDecimal price01, BigDecimal price02,int currentPage,int pageSize) throws Exception;
    //查询所有书的个数
    int countAllBooks() throws Exception;
    //返回当前页的books集合
    List<Book> findPageBooks(int currentPage,int currentCount) throws Exception;
    //查询搜索后书的个数
    int countSearchedBooks(Book book,BigDecimal price01,BigDecimal price02) throws Exception;
}
