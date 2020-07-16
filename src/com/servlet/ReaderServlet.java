package com.servlet;

import com.constants.Constants;
import com.domain.Reader;
import com.enums.ReaderAuthortyEnum;
import com.service.IReaderService;
import com.service.impl.ReaderService;
import com.util.PageBean;
import vo.PageReaderSearch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "ReaderServlet", urlPatterns = "/readerservlet")
public class ReaderServlet extends HttpServlet {
    /**
     * 创建一些类的对象
     */
    private IReaderService readerService = new ReaderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        /**
         * 中转站，action获取传过来的值执行不同操作
         */
        String action = request.getParameter("action");
        switch (action) {
            //  转到添加方法
            case "add":
                add(request, response);
                break;
            //转到删除方法
            case "delete":
                delete(request, response);
                break;
            //转到更新方法
            case "update":
                update(request, response);
                break;
            //转到查询方法
            case "search":
                try {
                    search(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            //返回主界面，清除session
            case "back":
                session.removeAttribute("readerName");
                session.removeAttribute("readerAccount");
                response.sendRedirect("Index/AdminIndex.jsp");
                break;
        }

    }

    /**
     * 通过搜索参数对读者数据进行查询
     *
     * @param request
     * @param response
     */
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //新建session
        HttpSession session=request.getSession();
        //获取请求中传过来的当前页码
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        //获取请求中传过来的总页数
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
        //获得常量每页数据条数
        int currentCount = Constants.CURRENTCOUNT;
        //获取请求中传过来的查询的参数,并进行判断
        String readerName = request.getParameter("readerName");
        String readerAccount = request.getParameter("readerAccount");
        //将查询数据存放到session当中
        if (readerName == null) {
            readerName="";
            Object object = session.getAttribute("readerName");
            if (object!=null){
                readerName=object.toString();
            }
        }else {
            session.removeAttribute("readerName");
            if (readerName!=""){
                session.setAttribute("readerName",readerName);
            }
        }

        if (readerAccount == null) {
            readerAccount = "";
            Object object = session.getAttribute("readerAccount");
            if (object!=null){
                readerAccount=object.toString();
            }
        }else {
            session.removeAttribute("readerAccount");
            if (readerAccount!=null){
                session.setAttribute("readerAccount",readerAccount);
            }
        }
        //并将其封装到搜索参数对象中
        PageReaderSearch pageReaderSearch = new PageReaderSearch(readerName, readerAccount);
        //调用readerService中的searchPageReader()方法，将查询参数对象和页码传进去,最终获取一个分页对象
        PageBean<Reader> readerPageBean = readerService.searchPageReader(pageReaderSearch, currentCount, currentPage);
        //将分页对象存储到request中
        request.setAttribute("readerPageBean", readerPageBean);
        //将分页对象返回到 ReaderManage.jsp 对象中
        request.getRequestDispatcher("Manage/ReaderManage.jsp").forward(request, response);
    }

    /**
     * 对读者信息进行更新
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String readerId = request.getParameter("readerId");
            String readerName = request.getParameter("readerName");
            String readerAccount = request.getParameter("readerAccount");
            String readerPassword = request.getParameter("readerPassword");
            int readerAuthorty = Integer.valueOf(request.getParameter("readerAuthorty"));
            Reader reader = new Reader(readerId, readerName, readerAccount, readerPassword, readerAuthorty);
            readerService.updateReader(reader);
            response.sendRedirect("adminindex?choice=2");
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对读者信息进行
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String readerId = request.getParameter("readerId");
        try {
            readerService.deleteReader(readerId);
            response.sendRedirect("adminindex?choice=2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("helssslo");
    }

    /**
     * 对读者信息进行添加
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //新建session
        HttpSession session=request.getSession();
        String readerId = UUID.randomUUID().toString();
        String readerName = request.getParameter("readerName");
        String readerAccount = request.getParameter("readerAccount");
        String readerPassword = request.getParameter("readerPassword");
        Reader reader = new Reader(readerId, readerName, readerAccount, readerPassword, ReaderAuthortyEnum.AVERAGE_USER.getCode());
        try {
            readerService.saveReader(reader);
            session.removeAttribute("readerName");
            session.removeAttribute("readerAccount");
            response.sendRedirect("readerservlet?action=search&currentPage=1");
        } catch (Exception e) {
            System.out.println("有异常");
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
