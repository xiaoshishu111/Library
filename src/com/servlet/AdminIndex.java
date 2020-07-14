package com.servlet;

import com.domain.Lend;
import com.domain.Reader;
import com.service.IBookService;
import com.service.ILendService;
import com.service.IReaderService;
import com.service.impl.BookService;
import com.service.impl.LendService;
import com.service.impl.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminIndex",urlPatterns = "/adminindex")
public class AdminIndex extends HttpServlet {
    private IBookService bookService=new BookService();
    private IReaderService readerService=new ReaderService();
    private ILendService lendService=new LendService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice=request.getParameter("choice");
        switch (choice) {
            case "1":
//                request.setAttribute("currentPage",1);
//                request.setAttribute("action","page");
                request.getRequestDispatcher("bookservlet?action=search&currentPage=1&status=2").forward(request,response);
//                response.sendRedirect("bookservlet");
                break;
            case "2":
                try {
                    List<Reader> readers=readerService.findAllReaders();
                    request.setAttribute("readers",readers);
                    request.getRequestDispatcher("ReaderManage.jsp").forward(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "3":
                try {
                    List<Lend> lends=lendService.findAllLends();
                    request.setAttribute("lends",lends);
                    request.getRequestDispatcher("LendManage.jsp").forward(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        PrintWriter out=response.getWriter();
        out.println("hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
