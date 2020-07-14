package com.servlet;

import com.domain.Book;
import com.enums.BookStatusEnum;
import com.service.IBookService;
import com.service.impl.BookService;
import com.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.UUID;

@WebServlet(name = "BookServlet",urlPatterns = "/bookservlet")
public class BookServlet extends HttpServlet {
    private IBookService bookService=new BookService();
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
                try {
                    search(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "page":
                try {
                    page(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                response.getWriter().println("hello");
                System.out.println("hello22");
                break;
        }

    }

    private void page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int currentPage=Integer.valueOf(request.getParameter("currentPage"));
//        System.out.println(currentPage);
        //对currentPage进行限制和判断
        int totalPage=bookService.pageBook(1).getTotalPage();
        if (currentPage<0||currentPage==0){
            currentPage=1;
        }else if (currentPage>totalPage||currentPage==totalPage){
            currentPage=totalPage;
        }
        PageBean<Book> bookPageBean=bookService.pageBook(currentPage);
        request.setAttribute("bookPageBean",bookPageBean);
        request.getRequestDispatcher("BookManage.jsp").forward(request,response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session=request.getSession();
        //将currentPage储存到session中
        int currentPage=Integer.valueOf(request.getParameter("currentPage"));
        session.setAttribute("currentPage",currentPage);
        //将bookName储存到session中
        String bookName=request.getParameter("bookName");
        if (bookName==null){
            bookName="";
        }
        session.setAttribute("bookName",bookName);
        //将bookAuthor存储到session中
        String bookAuthor=request.getParameter("bookAuthor");
        if (bookAuthor==null){
            bookAuthor="";
        }
        session.setAttribute("bookAuthor",bookAuthor);
        //将价格区间存储到session中
        String sprice01=request.getParameter("price01");
        if (sprice01==null){
            sprice01="";
        }

        String sprice02=request.getParameter("price02");
        if (sprice02==null){
            sprice02="";
        }

        BigDecimal price01= null;
        BigDecimal price02= null;

        if (sprice01!="" && sprice02!=""){
            price01= new BigDecimal(sprice01);
            price02= new BigDecimal(sprice02);
        }
        session.setAttribute("price01",price01);
        session.setAttribute("price02",price02);

        String sstatus=request.getParameter("status");
        int status;
        if (sstatus==null){
            status=2;
        }else {
            status=Integer.valueOf(sstatus);
        }
        session.setAttribute("status",status);


        String bookIdPara="";
        String bookNamePara=session.getAttribute("bookName").toString();
        String bookAuthorPara=session.getAttribute("bookAuthor").toString();
        BigDecimal pricePara=null;
        int statusPara=(Integer) session.getAttribute("status");
        Book book=new Book(bookIdPara,bookNamePara,bookAuthorPara,pricePara,statusPara);

        BigDecimal price01Para= (BigDecimal) session.getAttribute("price01");
        BigDecimal price02Para= (BigDecimal) session.getAttribute("price02");

        int currentPagePara= (int) session.getAttribute("currentPage");
        //对currentPage进行限制和判断
        int totalPage=bookService.pageBook(1).getTotalPage();
        if (currentPagePara<0||currentPagePara==0){
            currentPagePara=1;
        }else if (currentPagePara>totalPage||currentPagePara==totalPage){
            currentPagePara=totalPage;
        }
        PageBean<Book> bookPageBean=bookService.searchPageBook(book,price01Para,price02Para,currentPagePara);
        session.setAttribute("bookPageBean",bookPageBean);
        response.sendRedirect("BookManage.jsp");



//        String bookId="";
//        String bookName=request.getParameter("bookName");
//        if (bookName==null){
//            bookName="";
//        }
//        String bookAuthor=request.getParameter("bookAuthor");
//        if (bookAuthor==null){
//            bookAuthor="";
//        }
//        String sprice01=request.getParameter("price01");
//        if (sprice01==null){
//            sprice01="";
//        }
//        String sprice02=request.getParameter("price02");
//        if (sprice02==null){
//            sprice02="";
//        }
//
//        BigDecimal price01= null;
//        BigDecimal price02= null;
//
//        if (sprice01!="" && sprice02!=""){
//            price01= new BigDecimal(sprice01);
//            price02= new BigDecimal(sprice02);
//        }
//        BigDecimal price=null;
//        int status=0;
//        Book book=new Book(bookId,bookName,bookAuthor,price,status);
//        int currentPage=Integer.valueOf(request.getParameter("currentPage"));
//        System.out.println(currentPage);
//        //对currentPage进行限制和判断
//        int totalPage=bookService.pageBook(1).getTotalPage();
//        if (currentPage<0||currentPage==0){
//            currentPage=1;
//        }else if (currentPage>totalPage||currentPage==totalPage){
//            currentPage=totalPage;
//        }
//        PageBean<Book> bookPageBean=bookService.searchPageBook(book,price01,price02,currentPage);
//        session.setAttribute("bookPageBean",bookPageBean);
//        response.sendRedirect("BookManage.jsp");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out=response.getWriter();
        String bookId=request.getParameter("bookId");
        try {
            String bookName=request.getParameter("bookName");
            String author=request.getParameter("author");
            BigDecimal price=new BigDecimal(request.getParameter("price"));
            int status=Integer.valueOf(request.getParameter("status"));
            Book book=new Book(bookId,bookName,author,price, status);
            bookService.updateBook(book);
            request.getRequestDispatcher("adminindex?choice=1").forward(request,response);
            System.out.println(bookId);
        } catch (Exception e) {
            System.out.println("有异常");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String bookId=request.getParameter("bookId");
        try {
            bookService.deleteBook(bookId);
//            request.getRequestDispatcher("adminindex?choice=1").forward(request,response);
            page(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("helssslo");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookId=UUID.randomUUID().toString();
        String bookName=request.getParameter("bookName");
        String author=request.getParameter("author");
        BigDecimal price=new BigDecimal(request.getParameter("price"));
        Book book=new Book(bookId,bookName,author,price, BookStatusEnum.UNBORROWED.getCode());
        try {
            bookService.saveBook(book);
            request.getRequestDispatcher("adminindex?choice=1").forward(request,response);
        } catch (Exception e) {
            System.out.println("有异常");
        }
        System.out.println("hello");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out=response.getWriter();
//        out.println("hello");
        doPost(request,response);
    }
}
