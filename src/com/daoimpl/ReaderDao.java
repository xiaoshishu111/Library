package com.daoimpl;

import com.dao.IReaderDao;
import com.domain.Book;
import com.domain.Reader;
import com.domain.Reader;
import com.util.JDBCutils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReaderDao implements IReaderDao {
    @Override
    public void save(Reader reader) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="insert into t_reader values (?,?,?,?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,reader.getReaderId());
        pstmt.setObject(2,reader.getReaderName());
        pstmt.setObject(3,reader.getReaderAccount());
        pstmt.setObject(4,reader.getReaderPassword());
        pstmt.setObject(5,reader.getReaderAuthorty());
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public void delete(String readerId) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="delete from t_reader where readerId=?";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,readerId);
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public void update(Reader reader) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="UPDATE t_reader"+
                " SET readerName=?,readerAccount=?,readerPassword=?,readerAuthorty=? " +
                "WHERE readerId=?;";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,reader.getReaderName());
        pstmt.setObject(2,reader.getReaderAccount());
        pstmt.setObject(3,reader.getReaderPassword());
        pstmt.setObject(4,reader.getReaderAuthorty());
        pstmt.setObject(5,reader.getReaderId());
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection,pstmt,null);
    }

    @Override
    public List<Reader> findAll() throws Exception {
        List<Reader> list=new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from t_reader";
        //创建执行对象
        Statement statement=connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            String readerId  = rs.getString("readerId");
            String readerName = rs.getString("readerName");
            String readerAccount = rs.getString("readerAccount");
            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            Reader reader=new Reader(readerId,readerName,readerAccount,readerPassword,readerAuthorty);
            list.add(reader);

        }
        // 完成后释放资源

        JDBCutils.close(connection,statement,rs);
        return list;
    }


    @Override
    public Reader findById(String readerId) throws Exception {
        Reader reader=null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from  t_reader where readerId=?";
        //创建执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1,readerId);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String readerName = rs.getString("readerName");
            String readerAccount = rs.getString("readerAccount");
            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            reader=new Reader(readerId,readerName,readerAccount,readerPassword,readerAuthorty);
        }
        // 完成后释放资源
        JDBCutils.close(connection,pstmt,rs);
        return reader;
    }

    @Override
    public Reader findByAccount(String readerAccount) throws Exception {
        Reader reader=null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="select * from  t_reader where readerAccount=?";
        //创建执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1,readerAccount);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            // 通过字段检索
            String readerId = rs.getString("readerId");
            String readerName = rs.getString("readerName");

            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            reader=new Reader(readerId,readerName,readerAccount,readerPassword,readerAuthorty);
        }
        // 完成后释放资源
        JDBCutils.close(connection,pstmt,rs);
        return reader;
    }
}
