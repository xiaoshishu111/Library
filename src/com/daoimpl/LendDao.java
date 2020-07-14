package com.daoimpl;

import com.dao.ILendDao;
import com.domain.Book;
import com.domain.Lend;
import com.util.JDBCutils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LendDao implements ILendDao {
    @Override
    public void save(Lend lend) throws Exception {
        //加载jar包
        //加载驱动
//        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="insert into t_lend values (?,?,?,?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,lend.getLendId());
        pstmt.setObject(2,lend.getReaderId());
        pstmt.setObject(3,lend.getBookId());
        pstmt.setObject(4,lend.getBeginTime());
        pstmt.setObject(5,lend.getEndTime());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public List<Lend> findAll() throws Exception {
        List<Lend> list=new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from t_lend";
        //创建执行对象
        Statement statement=connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            String lendId  = rs.getString("lendId");
            String readerId = rs.getString("readerId");
            String bookId = rs.getString("bookId");
            Timestamp begintime = rs.getTimestamp("begintime");
            Timestamp endtime = rs.getTimestamp("endtime");
            //添加到对象中
            Lend lend=new Lend(lendId,readerId,bookId,begintime,endtime);
            list.add(lend);
        }
        // 完成后释放资源
        JDBCutils.close(connection,statement,rs);
        return list;
    }


    //查询还未被接走的书
    @Override
    public Lend findByBookId(String bookId) throws Exception{
        Lend lend=null;
        //加载jar包
        //加载驱动
        //Class.forName("com.mysql.jdbc.Driver");
        //创建连接
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from  t_lend where bookId=? and endtime is null ";
        //创建执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1,bookId);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String lendId  = rs.getString("lendId");
            String readerId = rs.getString("readerId");

            Timestamp begintime = rs.getTimestamp("begintime");
            Timestamp endtime = rs.getTimestamp("endtime");
            //添加到对象中
            lend=new Lend(lendId,readerId,bookId,begintime,endtime);

        }
        // 完成后释放资源
//        rs.close();
//        statement.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,rs);
        return lend;
    }

    @Override
    public void update(Lend lend) throws Exception {
        //加载jar包
        //加载驱动
//        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="UPDATE t_lend"+
                " SET bookId=?,readerId=?,begintime=?,endtime=?" +
                "WHERE lendId=?";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,lend.getBookId());
        pstmt.setObject(2,lend.getReaderId());
        pstmt.setObject(3,lend.getBeginTime());
        pstmt.setObject(4,lend.getEndTime());
        pstmt.setObject(5,lend.getLendId());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,null);
    }
}

