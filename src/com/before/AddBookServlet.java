package com.before;

import com.domain.Book;
import com.enums.BookStatusEnum;
import com.service.IBookService;
import com.serviceimpl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "AddBookServlet",urlPatterns ="/addbook")
public class AddBookServlet extends HttpServlet {
     IBookService bookService=new BookService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String bookId=request.getParameter("bookId");
        String bookName=request.getParameter("bookName");
        String author=request.getParameter("author");
        BigDecimal price=new BigDecimal(request.getParameter("price"));
        Book book=new Book(bookId,bookName,author,price, BookStatusEnum.UNBORROWED.getCode());
        try {
            bookService.saveBook(book);
        } catch (Exception e) {
            System.out.println("有异常");
        }

        PrintWriter out=response.getWriter();
        out.println(book.toString());
        out.println("<br><a href=\"index.jsp\">添加成功，返回主界面</a>");
        System.out.println("<a href='index.jsp'>W3School</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
