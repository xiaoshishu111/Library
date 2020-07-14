package com.before;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/index")
public class Index extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String choice=request.getParameter("choice");
        //选择相应的servlet进行处理
        switch (choice) {
            case "1":
                response.sendRedirect("before/AddBook.jsp");
                break;
            case "2":
                response.sendRedirect("before/UpdateBook.jsp");
                break;
            case "3":
                response.sendRedirect("before/DeleteBook.jsp");
                break;
            case "4":
                ShowAllBooksServlet showAllBooksServlet=new ShowAllBooksServlet();
                showAllBooksServlet.doPost(request,response);
                break;
        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
