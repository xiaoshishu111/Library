package com.service.impl;

import com.constants.Constants;
import com.dao.impl.BookDao;
import com.domain.Book;
import com.service.IBookService;
import com.util.PageBean;
import vo.BookVO;

import java.math.BigDecimal;
import java.util.List;

public class BookService implements IBookService {

    private BookDao bookDao = new BookDao();


    @Override
    public void saveBook(Book book) throws Exception {
        bookDao.save(book);
    }


    @Override
    public void deleteBook(String bookId) throws Exception {
        bookDao.delete(bookId);
    }


    @Override
    public void updateBook(Book book) throws Exception {
        bookDao.update(book);
    }


    @Override
    public List<Book> findAllbooks() throws Exception {
        return bookDao.findAll();
    }


    @Override
    public Book findBook(String bookId) throws Exception {
        return bookDao.findById(bookId);
    }

    @Override
    public PageBean<Book> searchPageReader(BookVO bookVO) throws Exception {
        return bookDao.findPageReader(bookVO);
    }


}
