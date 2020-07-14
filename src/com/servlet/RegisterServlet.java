package com.servlet;

import com.domain.Reader;
import com.service.IReaderService;
import com.service.impl.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Register",urlPatterns = "/registerservlet")
public class RegisterServlet extends HttpServlet {
    private IReaderService readerService=new ReaderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String readerAccount=request.getParameter("readerAccount");
        String readerPassword=request.getParameter("readerPassword");
        String readerName=request.getParameter("readerName");
        try {
            if (readerService.findReaderByAccount(readerAccount)==null){
                Reader reader=new Reader(UUID.randomUUID().toString(),readerName,readerAccount,readerPassword,0);
                readerService.saveReader(reader);
                request.setAttribute("result","账号注册成功");
                request.getRequestDispatcher("Register.jsp").forward(request,response);
            }else {
                request.setAttribute("result","此账号已经被注册，请重新输入");
                request.getRequestDispatcher("Register.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
