package com.servlet;

import com.constants.Constants;
import com.domain.Book;
import com.domain.Lend;
import com.domain.Reader;
import com.enums.BookStatusEnum;
import com.service.IBookService;
import com.service.ILendService;
import com.service.IReaderService;
import com.service.impl.BookService;
import com.service.impl.LendService;
import com.service.impl.ReaderService;
import com.util.PageBean;
import vo.BookVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "BookServlet", urlPatterns = "/bookservlet")
public class BookServlet extends HttpServlet {
    private IBookService bookService = new BookService();
    private IReaderService readerService=new ReaderService();
    private ILendService lendService=new LendService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                add(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "search":
                try {
                    search(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                delete(request, response);
                break;
            case "borrow":
                try {
                    borrow(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "returnbook":
                try {
                    returnbook(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "backAdmin":
                //清除session中的记录
                session.removeAttribute("bookName");
                session.removeAttribute("bookAuthor");
                session.removeAttribute("priceMin");
                session.removeAttribute("priceMax");
                session.removeAttribute("status");
                response.sendRedirect("Index/AdminIndex.jsp");
                break;
            case "backUser":
                //清除session中的记录
                session.removeAttribute("bookName");
                session.removeAttribute("bookAuthor");
                session.removeAttribute("priceMin");
                session.removeAttribute("priceMax");
                response.sendRedirect("Index/UserIndex.jsp");
                break;
        }

    }

    private void returnbook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String readerId=request.getParameter("readerId");
        String bookId=request.getParameter("bookId");
        String lendId=null;
        Timestamp dateEnd=new Timestamp(new Date().getTime());
        Timestamp dateBegin=null;
        if (bookService.findBook(bookId)==null){
            request.setAttribute("result","没找到此书");
        }else {
            Lend lend=new Lend(lendId,readerId,bookId,dateBegin,dateEnd);
            lendService.updateLend(lend);
            request.setAttribute("result","归还成功");
        }
        request.getRequestDispatcher("Service/ReturnBook.jsp").forward(request,response);

    }

    private void borrow(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookId=request.getParameter("bookId");
        String readerId=request.getParameter("readerId");
        Book book=bookService.findBook(bookId);
        book.setStatus(1);
        bookService.updateBook(book);
        Reader reader=readerService.findReader(readerId);
        Timestamp dateBegin=new Timestamp(new Date().getTime());
        Timestamp dateEnd=null;
        String uuid=UUID.randomUUID().toString();
        Lend lend=new Lend(uuid,readerId,bookId,dateBegin,dateEnd);
        lendService.saveLend(lend);
        response.sendRedirect("normalindex?choice=1");
    }


    private void search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户的服务需求
        String service = request.getParameter("service");
        //创建session
        HttpSession session = request.getSession();
        //获取搜索框中的参数
        String bookName = request.getParameter("bookName");
        String bookAuthor = request.getParameter("bookAuthor");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");
        String status = request.getParameter("status");
        //获取请求中传过来的当前页码
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        //获取请求传来的总页数
        String total = request.getParameter("totalPage");
        //对当前页数进行限制
        if (total != null) {
            int totalPage = Integer.valueOf(total);
            if (currentPage < 0 || currentPage == 0) {
                currentPage = 1;
            } else if (currentPage > totalPage || currentPage == totalPage) {
                currentPage = totalPage;
            }
        }
        //将搜索框中的参数参访到session中去
        //存放书名
        if (bookName == null) {
            bookName = "";
            Object object = session.getAttribute("bookName");
            if (object != null) {
                bookName = object.toString();
            }
        } else {
            session.removeAttribute("bookName");
            if (bookName != "") {
                session.setAttribute("bookName", bookName);
            }
        }
        //存放书的作者
        if (bookAuthor == null) {
            bookAuthor = "";
            Object object = session.getAttribute("bookAuthor");
            if (object != null) {
                bookAuthor = object.toString();
            }
        } else {
            session.removeAttribute("bookAuthor");
            if (bookAuthor != "") {
                session.setAttribute("bookAuthor", bookAuthor);
            }
        }
        //存放价格
        BigDecimal price01 = null;
        BigDecimal price02 = null;
        if (priceMin == null) {
            Object object = session.getAttribute("priceMin");
            if (object != null) {
                price01 = new BigDecimal((String) object);
            }
        } else {
            session.removeAttribute("priceMin");
            if (priceMin != "") {
                price01 = new BigDecimal(priceMin);
                session.setAttribute("priceMin", priceMin);
            }
        }
        if (priceMax == null) {
            Object object = session.getAttribute("priceMax");
            if (object != null) {
                price02 = new BigDecimal((String) object);
            }
        } else {
            session.removeAttribute("priceMax");
            if (priceMax != "") {
                price02 = new BigDecimal(priceMax);
                session.setAttribute("priceMax", priceMax);
            }
        }

        int statusPara = 2;
        if (status == null) {
            Object object = session.getAttribute("status");
            if (object != null) {
                statusPara = Integer.parseInt(object.toString());
                ;
            }
        } else {
            session.removeAttribute("status");
            if (status != "2") {
                statusPara = Integer.valueOf(status);
                session.setAttribute("status", status);
            }
        }


        int currentCount = Constants.CURRENTCOUNT;

        if (service == null) {
            BookVO bookVO = new BookVO(bookName, bookAuthor, price01, price02, statusPara, currentCount, currentPage);
            PageBean<Book> bookPageBean = bookService.searchPageReader(bookVO);
            request.setAttribute("bookPageBean", bookPageBean);
            request.getRequestDispatcher("Manage/BookManage.jsp").forward(request, response);
        } else {
            BookVO bookVOBorrow = new BookVO(bookName, bookAuthor, price01, price02, 0, currentCount, currentPage);
            PageBean<Book> bookPageBeanBorrow = bookService.searchPageReader(bookVOBorrow);
            request.setAttribute("bookPageBean", bookPageBeanBorrow);
            request.getRequestDispatcher("Service/BorrowBook.jsp").forward(request, response);
        }


    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        try {
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            int status = Integer.valueOf(request.getParameter("status"));
            Book book = new Book(bookId, bookName, author, price, status);
            bookService.updateBook(book);
            //清除session中的记录
            session.removeAttribute("bookName");
            session.removeAttribute("bookAuthor");
            session.removeAttribute("priceMin");
            session.removeAttribute("priceMax");
            session.removeAttribute("status");
            response.sendRedirect("adminindex?choice=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        try {
            bookService.deleteBook(bookId);
            //清除session中的记录
            session.removeAttribute("bookName");
            session.removeAttribute("bookAuthor");
            session.removeAttribute("priceMin");
            session.removeAttribute("priceMax");
            session.removeAttribute("status");
            response.sendRedirect("adminindex?choice=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String bookId = UUID.randomUUID().toString();
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Book book = new Book(bookId, bookName, author, price, BookStatusEnum.UNBORROWED.getCode());
        try {
            bookService.saveBook(book);
            //清除session中的记录
            session.removeAttribute("bookName");
            session.removeAttribute("bookAuthor");
            session.removeAttribute("priceMin");
            session.removeAttribute("priceMax");
            session.removeAttribute("status");
            response.sendRedirect("adminindex?choice=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out=response.getWriter();
//        out.println("hello");
        doPost(request, response);
    }
}
