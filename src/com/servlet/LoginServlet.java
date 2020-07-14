package com.servlet;

import com.domain.Reader;
import com.service.IReaderService;
import com.service.impl.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginservlet")
public class LoginServlet extends HttpServlet {
    private IReaderService readerService=new ReaderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action){
            case "0":
                normalReader(request,response);
                break;
            case "1":
                adminReader(request,response);
                break;
        }


    }

    private void adminReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String readerAccount=request.getParameter("readerAccount");
        String readerPassword=request.getParameter("readerPassword");
        try {
            Reader reader=readerService.findReaderByAccount(readerAccount);
            if (reader==null){
                request.setAttribute("isreader","0");
                request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
            }else {
                if (readerPassword.equals(reader.getReaderPassword())){
                    if (reader.getReaderAuthorty()==1){
                        session.setAttribute("AdminReader",reader);
                        request.getRequestDispatcher("AdminIndex.jsp").forward(request,response);
                    }else {
                        request.setAttribute("isreader","1");
                        request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
                    }
                }else {
                    request.setAttribute("isreader","2");
                    request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void normalReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String readerAccount=request.getParameter("readerAccount");
        String readerPassword=request.getParameter("readerPassword");
        try {
            Reader reader=readerService.findReaderByAccount(readerAccount);
            if (reader==null){
                request.setAttribute("isreader","0");
                request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
            }else {
                if (readerPassword.equals(reader.getReaderPassword())){
                    session.setAttribute("NormalReader",reader);
                    response.sendRedirect("UserIndex.jsp");
                }else {
                    request.setAttribute("isreader","1");
                    request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
