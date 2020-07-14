package com.serviceimpl;

import com.daoimpl.BookDao;
import com.domain.Book;
import com.enums.BookStatusEnum;
import com.service.IBookService;
import com.util.PageBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BookService implements IBookService {

    private BookDao bookDao=new BookDao();


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
    public PageBean<Book> searchPageBook(Book book,BigDecimal price01,BigDecimal price02,int currentPage) throws Exception {
        int currentCount=10;
        int totalCount=bookDao.countSearchedBooks(book,price01,price02);
        int result=totalCount%currentCount;
        int totalPage;
        if (result==0){
            totalPage=totalCount/currentCount;
        }else {
            totalPage=(totalCount/currentCount)+1;
        }
        List<Book> books=bookDao.searchBooks(book,price01,price02,currentPage,currentCount);
        PageBean<Book> bookPageBean=new PageBean<>(currentPage,currentCount,totalPage,totalCount,books);
        return bookPageBean;
    }

    @Override
    public PageBean<Book> pageBook(int currentPage) throws Exception {
        int currentCount=10;
        int totalCount=bookDao.countAllBooks();
        //通过求余求总页数
        int result=totalCount%currentCount;
        int totalPage;
        if (result==0){
            totalPage=totalCount/currentCount;
        }else {
            totalPage=(totalCount/currentCount)+1;
        }
        List<Book> books=bookDao.findPageBooks(currentPage,currentCount);
        PageBean<Book> bookPageBean=new PageBean<>(currentPage,currentCount,totalPage,totalCount,books);
        return bookPageBean;
    }


}
