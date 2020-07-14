package com.servlet;

import com.constants.Constants;
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
import java.util.UUID;

@WebServlet(name = "UserServlet", urlPatterns = "/userservlet")
public class UserServlet extends HttpServlet {
    /**
     * 生成一些类的对象
     */
    IReaderService readerService = new ReaderService();

    /**
     * post方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取action值
        String action = request.getParameter("action");
        /**
         * 方法中转站
         */
        switch (action) {
            //转到普通用户登陆方法
            case "userLogin":
                userLogin(request, response);
                break;
            //转到管理员用户登陆方法
            case "adminLogin":
                adminLogin(request, response);
                break;
            //转到用户注册方法
            case "register":
                register(request, response);
                break;
            //转到用户退出方法
            case "logout":
                logut(request, response);
                break;
        }
    }

    /**
     * 用户退出方法
     *
     * @param request
     * @param response
     */
    private void logut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        //如果不为空则清除
        if (session.getAttribute("Admin") != null) {
            session.removeAttribute("Admin");
        }
        if (session.getAttribute("User") != null) {
            session.removeAttribute("User");
        }
        //貌似只能用这个方法，重定向似乎不好用
        request.getRequestDispatcher("Login/UserLogin.jsp").forward(request,response);
    }

    /**
     * 用户注册方法
     *
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        /**
         * 获取readerAccount，readerPassword，readerName的参数
         */
        String readerAccount = request.getParameter("readerAccount");
        String readerPassword = request.getParameter("readerPassword");
        String readerName = request.getParameter("readerName");
        /**
         * 判断账号是否已经存在，如果不存在，则保存用户信息
         */
        try {
            if (readerService.findReaderByAccount(readerAccount) == null) {
                Reader reader = new Reader(UUID.randomUUID().toString(), readerName, readerAccount, readerPassword, 0);
                readerService.saveReader(reader);
                request.setAttribute("result", "账号注册成功");
                //向session中添加普通用户信息
                session.setAttribute("User", reader);
                request.getRequestDispatcher("Index/UserIndex.jsp").forward(request, response);
            } else {
                request.setAttribute("result", "此账号已经被注册，请重新输入");
                request.getRequestDispatcher("Register/Register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员用户登陆处理
     *
     * @param request
     * @param response
     */
    private void adminLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String readerAccount = request.getParameter("readerAccount");
        String readerPassword = request.getParameter("readerPassword");
        try {
            Reader reader = readerService.findReaderByAccount(readerAccount);
            if (reader == null) {
                request.setAttribute("isreader", "0");
                request.getRequestDispatcher("Login/AdminLogin.jsp").forward(request, response);
            } else {
                if (readerPassword.equals(reader.getReaderPassword())) {
                    if (reader.getReaderAuthorty() == 1) {
                        //session中添加管理员用户信息
                        session.setAttribute("Admin", reader);
                        request.getRequestDispatcher("Index/AdminIndex.jsp").forward(request, response);
                    } else {
                        request.setAttribute("isreader", "1");
                        request.getRequestDispatcher("Login/AdminLogin.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("isreader", "2");
                    request.getRequestDispatcher("Login/AdminLogin.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通用户登陆处理
     *
     * @param request
     * @param response
     */
    private void userLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String readerAccount = request.getParameter("readerAccount");
        String readerPassword = request.getParameter("readerPassword");
        try {
            Reader reader = readerService.findReaderByAccount(readerAccount);
            if (reader == null) {
                request.setAttribute("isreader", "0");
                request.getRequestDispatcher("Login/UserLogin.jsp").forward(request, response);
            } else {
                if (readerPassword.equals(reader.getReaderPassword())) {
                    //session添加普通用户值
                    session.setAttribute("User", reader);
                    response.sendRedirect("Index/UserIndex.jsp");
                } else {
                    request.setAttribute("isreader", "1");
                    request.getRequestDispatcher("Login/UserLogin.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
