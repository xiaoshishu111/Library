package com.dao.impl;

import com.dao.IBookDao;
import com.domain.Book;
import com.util.JDBCutils;
import com.util.PageBean;
import vo.BookVO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    @Override
    public void save(Book book) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "insert into t_book values (?,?,?,?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, book.getBookId());
        pstmt.setObject(2, book.getBookName());
        pstmt.setObject(3, book.getBookAuthor());
        pstmt.setObject(4, book.getPrice());
        pstmt.setObject(5, book.getStatus());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection, pstmt, null);
    }

    @Override
    public void delete(String bookId) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "delete from t_book where bookId=?";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, bookId);
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
        JDBCutils.close(connection, pstmt, null);
    }


    @Override
    public void update(Book book) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "UPDATE t_book" +
                " SET bookName=?,bookAuthor=?,price=?,status=? " +
                "WHERE bookId=?;";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, book.getBookName());
        pstmt.setObject(2, book.getBookAuthor());
        pstmt.setObject(3, book.getPrice());
        pstmt.setObject(4, book.getStatus());
        pstmt.setObject(5, book.getBookId());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
        JDBCutils.close(connection, pstmt, null);
    }

    @Override
    public List<Book> findAll() throws Exception {
        List<Book> list = new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "select * from t_book";
        //创建执行对象
        Statement statement = connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            // 通过字段检索
            String bookId = rs.getString("bookId");
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            Book book = new Book(bookId, bookName, bookAuthor, price, status);
            list.add(book);

        }
        // 完成后释放资源
        JDBCutils.close(connection, statement, rs);
        return list;
    }

    @Override
    public Book findById(String bookId) throws Exception {
        Book book = null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "select * from  t_book where bookId=?";
        //创建执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1, bookId);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            // 通过字段检索
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            book = new Book(bookId, bookName, bookAuthor, price, status);
        }
        // 完成后释放资源
        JDBCutils.close(connection, pstmt, rs);
        return book;
    }

    @Override
    public PageBean<Book> findPageReader(BookVO bookVO) throws Exception {
        //创建list来存放book对象
        List<Book> list = new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入两条sql语句，一条求总个数，一条求分页的数据
        String sql1="select count(*) from t_book where 1=1";
        String sql2 = "select * from t_book where 1=1";
        //用paraList存放?数据
        List paraList = new ArrayList<>();
        //对sql进行修改
        if (!bookVO.getBookName().equals("")) {
            sql1 += " and bookName like ?";
            sql2 += " and bookName like ?";
            String str = "%" + bookVO.getBookName() + "%";
            paraList.add(str);
        }
        if (!bookVO.getBookAuthor().equals("")) {
            sql1 += " and bookAuthor like ?";
            sql2 += " and bookAuthor like ?";
            String str = "%" + bookVO.getBookAuthor() + "%";
            paraList.add(str);
        }
        if (bookVO.getPriceMin() != null && bookVO.getPriceMax() != null) {
            sql1 += " and price between ? and ?";
            sql2 += " and price between ? and ?";
            BigDecimal str1 = bookVO.getPriceMin();
            BigDecimal str2 = bookVO.getPriceMax();
            paraList.add(str1);
            paraList.add(str2);
        }
        if (bookVO.getStatus() != 2) {
            sql1 += " and status=?";
            sql2 += " and status=?";
            int str = bookVO.getStatus();
            paraList.add(str);
        }
        //此次预编译求总个数
        PreparedStatement pstmt1 = connection.prepareStatement(sql1);
        int i=1;
        for (Object object : paraList) {
            pstmt1.setObject(i, object);
            i++;
        }
        ResultSet rs1 = pstmt1.executeQuery();
        rs1.next();
        //获得总条数
        int totalCount=rs1.getInt(1);

        //取出分页bean
        sql2 += " limit ?,?";
        int numBegin = (bookVO.getCurrentPage() - 1) * bookVO.getCurrentCount();
        int numEnd = bookVO.getCurrentCount();
        paraList.add(numBegin);
        paraList.add(numEnd);
        //开始预编译
        PreparedStatement pstmt2 = connection.prepareStatement(sql2);
        int j = 1;
        for (Object object : paraList) {
            pstmt2.setObject(j, object);
            j++;
        }

        ResultSet rs2 = pstmt2.executeQuery();
        while (rs2.next()) {
            // 通过字段检索
            String bookId = rs2.getString("bookId");
            String bookName = rs2.getString("bookName");
            String bookAuthor = rs2.getString("bookAuthor");
            BigDecimal price = rs2.getBigDecimal("price");
            int status = rs2.getInt("status");
            //添加到对象中
            Book searchedbook = new Book(bookId, bookName, bookAuthor, price, status);
            list.add(searchedbook);
        }
        // 完成后释放资源
        JDBCutils.close(connection, pstmt1, rs2);
        //封装分页对象
        int totalPage=0;
        PageBean<Book> bookPageBean=new PageBean<>(bookVO.getCurrentPage(),bookVO.getCurrentCount(),totalPage,totalCount,list);
        return bookPageBean;
    }


}
