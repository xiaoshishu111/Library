package com.servlet;

import com.domain.Reader;
import com.enums.ReaderAuthortyEnum;
import com.service.IReaderService;
import com.service.impl.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "ReaderServlet",urlPatterns = "/readerservlet")
public class ReaderServlet extends HttpServlet {
    private IReaderService readerService=new ReaderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action) {
            case "add":
                add(request,response);
                break;
            case "delete":
                delete(request,response);
                System.out.println("hellow");
                break;
            case "update":
                System.out.println("11");
                update(request,response);
                System.out.println("helle11");
                break;
            case "search":
                break;
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out=response.getWriter();

        try {
            String readerId=request.getParameter("readerId");
            String readerName=request.getParameter("readerName");
            String readerAccount=request.getParameter("readerAccount");
            String readerPassword=request.getParameter("readerPassword");
            int readerAuthorty=Integer.valueOf(request.getParameter("readerAuthorty"));
            Reader reader=new Reader(readerId,readerName,readerAccount,readerPassword,readerAuthorty);
            readerService.updateReader(reader);
            request.getRequestDispatcher("adminindex?choice=2").forward(request,response);
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String readerId=request.getParameter("readerId");
        try {
            readerService.deleteReader(readerId);
            request.getRequestDispatcher("adminindex?choice=2").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("helssslo");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String readerId= UUID.randomUUID().toString();
        String readerName=request.getParameter("readerName");
        String readerAccount=request.getParameter("readerAccount");
        String readerPassword=request.getParameter("readerPassword");
        Reader reader=new Reader(readerId,readerName,readerAccount,readerPassword, ReaderAuthortyEnum.AVERAGE_USER.getCode());
        try {
            readerService.saveReader(reader);
            request.getRequestDispatcher("adminindex?choice=2").forward(request,response);
        } catch (Exception e) {
            System.out.println("有异常");
        }
        System.out.println("hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
