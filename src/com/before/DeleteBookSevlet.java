package com.before;

import com.serviceimpl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteBookSevlet",urlPatterns = "/deletebook")
public class DeleteBookSevlet extends HttpServlet {
    private BookService bookService=new BookService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String bookId=request.getParameter("bookId");
        try {
            bookService.deleteBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("删除成功");
        PrintWriter out=response.getWriter();
        out.println("<br><a href=\"index.jsp\">删除成功，返回主界面</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
