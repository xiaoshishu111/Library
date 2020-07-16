package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NormalServlet",urlPatterns = "/normalindex")
public class NormalIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice=request.getParameter("choice");
        switch (choice){
            case "1":
                request.getRequestDispatcher("bookservlet?action=search&currentPage=1&service=user").forward(request,response);
            case "2":
                response.sendRedirect("Service/ReturnBook.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
