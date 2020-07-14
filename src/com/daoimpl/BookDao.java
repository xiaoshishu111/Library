package com.daoimpl;

import com.dao.IBookDao;
import com.domain.Book;
import com.domain.User;
import com.util.JDBCutils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    @Override
    public void save(Book book) throws Exception{
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="insert into t_book values (?,?,?,?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,book.getBookId());
        pstmt.setObject(2,book.getBookName());
        pstmt.setObject(3,book.getBookAuthor());
        pstmt.setObject(4,book.getPrice());
        pstmt.setObject(5,book.getStatus());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public void delete(String bookId) throws Exception{
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="delete from t_book where bookId=?";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,bookId);
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
        JDBCutils.close(connection,pstmt,null);
    }


    @Override
    public void update(Book book) throws Exception{
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="UPDATE t_book"+
                " SET bookName=?,bookAuthor=?,price=?,status=? " +
                "WHERE bookId=?;";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,book.getBookName());
        pstmt.setObject(2,book.getBookAuthor());
        pstmt.setObject(3,book.getPrice());
        pstmt.setObject(4,book.getStatus());
        pstmt.setObject(5,book.getBookId());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public List<Book> findAll() throws Exception{
        List<Book> list=new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from t_book";
        //创建执行对象
        Statement statement=connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            String bookId  = rs.getString("bookId");
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            Book book=new Book(bookId,bookName,bookAuthor,price,status);
            list.add(book);

        }
        // 完成后释放资源
        JDBCutils.close(connection,statement,rs);
        return list;
    }

    @Override
    public Book findById(String bookId) throws Exception{
        Book book=null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from  t_book where bookId=?";
        //创建执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1,bookId);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            book=new Book(bookId,bookName,bookAuthor,price,status);
        }
        // 完成后释放资源
        JDBCutils.close(connection,pstmt,rs);
        return book;
    }

    @Override
    public List<Book> searchBooks(Book book,BigDecimal price01,BigDecimal price02,int currentPage,int currentCount) throws Exception {
        List<Book> list=new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        PreparedStatement pstmt;
        //输入sql语句
        String sql="select * from t_book where 1=1 ";
        List<Object> strings=new ArrayList<>();
        if (!book.getBookName().equals("")){
            sql+="and bookName like ? ";
            String str="%"+book.getBookName()+"%";
            strings.add(str);
            System.out.println(sql);
        }

        if (!book.getBookAuthor().equals("")){
            sql+="and bookAuthor like ? ";
            String str="%"+book.getBookAuthor()+"%";
            strings.add(str);
        }

        if (price01!=null && price02!=null){
            sql+="and price between ? and ? ";
            BigDecimal str1=price01;
            BigDecimal str2=price02;
            strings.add(str1);
            strings.add(str2);
        }

        if (book.getStatus()!=2){
            sql+="and status=? ";
            int str=book.getStatus();
            strings.add(str);
        }




        //取出分页bean
        sql+="limit ?,?";
        int num1=(currentPage-1)*currentCount;
        int num2=currentCount;
        strings.add(num1);
        strings.add(num2);

        int j=1;
        pstmt=connection.prepareStatement(sql);
        for (Object object:strings){
            pstmt.setObject(j,object);
            j++;
        }
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String bookId  = rs.getString("bookId");
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            Book searchedbook=new Book(bookId,bookName,bookAuthor,price,status);
            list.add(searchedbook);
        }
        // 完成后释放资源
        JDBCutils.close(connection,pstmt,rs);
        return list;
    }

    @Override
    public int countAllBooks() throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select count(*) from t_book";
        //创建执行对象
        Statement statement=connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        int totalCount=rs.getInt(1);
//        int totalCount=0;
//        while (rs.next()){
//            totalCount++;
//        }
        // 完成后释放资源
        JDBCutils.close(connection,statement,rs);
        return totalCount;
    }

    @Override
    public List<Book> findPageBooks(int currentPage, int pageSize) throws Exception {
        List<Book> books=new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from  t_book limit ?,?";
        //创建执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1,(currentPage-1)*pageSize);
        pstmt.setObject(2,pageSize);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String bookId=rs.getString("bookId");
            String bookName = rs.getString("bookName");
            String bookAuthor = rs.getString("bookAuthor");
            BigDecimal price = rs.getBigDecimal("price");
            int status = rs.getInt("status");
            //添加到对象中
            Book book=new Book(bookId,bookName,bookAuthor,price,status);
            books.add(book);
        }
        // 完成后释放资源
        JDBCutils.close(connection,pstmt,rs);
        return books;
    }

    @Override
    public int countSearchedBooks(Book book,BigDecimal price01,BigDecimal price02) throws Exception {
        Connection connection = JDBCutils.getConnection();
        PreparedStatement pstmt;
        //输入sql语句
        String sql="select * from t_book where 1=1 ";
        List<Object> strings=new ArrayList<>();
        if (!book.getBookName().equals("")){
            sql+="and bookName like ? ";
            String str="%"+book.getBookName()+"%";
            strings.add(str);
            System.out.println(sql);
        }

        if (!book.getBookAuthor().equals("")){
            sql+="and bookAuthor like ? ";
            String str="%"+book.getBookAuthor()+"%";
            strings.add(str);
        }

        if (price01!=null && price02!=null){
            sql+="and price between ? and ? ";
            BigDecimal str1=price01;
            BigDecimal str2=price02;
            strings.add(str1);
            strings.add(str2);
        }

        sql+="and status=? ";
        int str=book.getStatus();
        strings.add(str);

        //计算总个数
        int i=1;
        pstmt=connection.prepareStatement(sql);
        for (Object object:strings){
            pstmt.setObject(i,object);
            i++;
        }
        ResultSet rs = pstmt.executeQuery();
        int totalCount=0;
        while (rs.next()){
            totalCount++;
        }
        return totalCount;
    }
}
