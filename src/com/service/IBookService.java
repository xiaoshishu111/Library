package com.service;

import com.domain.Book;
import com.util.PageBean;
import vo.BookVO;

import java.math.BigDecimal;
import java.util.List;

public interface IBookService {
    //添加书本
    void saveBook(Book book) throws Exception;
    //删除书本
    void deleteBook(String bookId) throws Exception;
    //更新书本信息
    void updateBook(Book book) throws Exception;
    //查询所有书本信息
    List<Book> findAllbooks() throws Exception;
    //查询单本书的信息
    Book findBook(String bookId) throws Exception;
    //根据查询条件返回一条分页对象
    PageBean<Book> searchPageReader(BookVO bookVO) throws Exception;
}
