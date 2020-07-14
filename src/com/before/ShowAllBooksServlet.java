package com.before;

import com.domain.Book;
import com.serviceimpl.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ShowAllServlet",urlPatterns = "/showbooks")
public class ShowAllBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        try {
            List<Book> books=new BookService().findAllbooks();
            for (Book book:books){
                out.println(book.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("<br><a href=\"index.jsp\">返回主界面</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
