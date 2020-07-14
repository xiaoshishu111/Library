package com.before;

import com.domain.Book;
import com.enums.BookStatusEnum;
import com.serviceimpl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "UpdateBookServlet",urlPatterns = "/updatebook")
public class UpdateBookServlet extends HttpServlet {
    private BookService bookService=new BookService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


/*        String bookName=request.getParameter("bookName");
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
        System.out.println("添加成功");*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String bookId=request.getParameter("bookId");
        try {
            Book isbook=bookService.findBook(bookId);
            if (isbook==null){
                out.println("没有找到您输入的书本id：");
                out.println("<br><a href=\"index.jsp\">返回主界面</a>");
            }else {
                String bookName=request.getParameter("bookName");
                String author=request.getParameter("author");
                BigDecimal price=new BigDecimal(request.getParameter("price"));
                int status=Integer.valueOf(request.getParameter("status"));
                Book book=new Book(bookId,bookName,author,price, status);
                bookService.updateBook(book);
                out.println(book.toString());
                out.println("<br><a href=\"index.jsp\">更新成功，返回主界面</a>");
                System.out.println("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
