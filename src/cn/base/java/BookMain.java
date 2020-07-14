package cn.base.java;

import com.daoimpl.BookDao;
import com.daoimpl.UserDao;
import com.domain.Book;
import com.domain.User;
import com.enums.BookStatusEnum;
import com.service.IBookService;
import com.serviceimpl.BookService;
import com.util.PageBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookMain {

    public static void main(String[] args) throws Exception {
        BookDao bookDao=new BookDao();
        int i=bookDao.countAllBooks();
        System.out.println(i);
    }
}