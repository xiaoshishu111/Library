package cn.base.java;

import com.dao.impl.BookDao;

public class BookMain {

    public static void main(String[] args) throws Exception {
        BookDao bookDao=new BookDao();
        int i=bookDao.countAllBooks();
        System.out.println(i);
    }
}