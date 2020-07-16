package com.dao.impl;

import com.dao.IReaderDao;
import com.domain.Reader;
import com.util.JDBCutils;
import vo.PageReaderSearch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDao implements IReaderDao {
    @Override
    public void save(Reader reader) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "insert into t_reader values (?,?,?,?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, reader.getReaderId());
        pstmt.setObject(2, reader.getReaderName());
        pstmt.setObject(3, reader.getReaderAccount());
        pstmt.setObject(4, reader.getReaderPassword());
        pstmt.setObject(5, reader.getReaderAuthorty());
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection, pstmt, null);
    }

    @Override
    public void delete(String readerId) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "delete from t_reader where readerId=?";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, readerId);
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection, pstmt, null);
    }

    @Override
    public void update(Reader reader) throws Exception {
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "UPDATE t_reader" +
                " SET readerName=?,readerAccount=?,readerPassword=?,readerAuthorty=? " +
                "WHERE readerId=?;";
        //创建预处理执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1, reader.getReaderName());
        pstmt.setObject(2, reader.getReaderAccount());
        pstmt.setObject(3, reader.getReaderPassword());
        pstmt.setObject(4, reader.getReaderAuthorty());
        pstmt.setObject(5, reader.getReaderId());
        //执行sql语句
        pstmt.executeUpdate();
        JDBCutils.close(connection, pstmt, null);
    }

    @Override
    public List<Reader> findAll() throws Exception {
        List<Reader> list = new ArrayList<>();
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "select * from t_reader";
        //创建执行对象
        Statement statement = connection.createStatement();
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            // 通过字段检索
            String readerId = rs.getString("readerId");
            String readerName = rs.getString("readerName");
            String readerAccount = rs.getString("readerAccount");
            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            Reader reader = new Reader(readerId, readerName, readerAccount, readerPassword, readerAuthorty);
            list.add(reader);

        }
        // 完成后释放资源

        JDBCutils.close(connection, statement, rs);
        return list;
    }


    @Override
    public Reader findById(String readerId) throws Exception {
        Reader reader = null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "select * from  t_reader where readerId=?";
        //创建执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1, readerId);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            // 通过字段检索
            String readerName = rs.getString("readerName");
            String readerAccount = rs.getString("readerAccount");
            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            reader = new Reader(readerId, readerName, readerAccount, readerPassword, readerAuthorty);
        }
        // 完成后释放资源
        JDBCutils.close(connection, pstmt, rs);
        return reader;
    }

    @Override
    public Reader findByAccount(String readerAccount) throws Exception {
        Reader reader = null;
        //创建连接
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql = "select * from  t_reader where readerAccount=?";
        //创建执行对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //给?赋值
        pstmt.setObject(1, readerAccount);
        //执行sql语句
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            // 通过字段检索
            String readerId = rs.getString("readerId");
            String readerName = rs.getString("readerName");

            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            //添加到对象中
            reader = new Reader(readerId, readerName, readerAccount, readerPassword, readerAuthorty);
        }
        // 完成后释放资源
        JDBCutils.close(connection, pstmt, rs);
        return reader;
    }

    /**
     * 返回readers数据集合
     *
     * @param pageReaderSearch
     * @param currentCount
     * @param currentPage
     * @return
     */
    @Override
    public List<Reader> findReaders(PageReaderSearch pageReaderSearch, int currentCount, int currentPage) throws SQLException {
        //创建数据库连接
        Connection connection = JDBCutils.getConnection();
        //创建list来存放reader数据
        List<Reader> list=new ArrayList<>();
        //书写初始sql语句
        String sql = "select * from t_reader where 1=1";
        //创建paraList来存放？数据
        List paraList = new ArrayList();

        /**
         * 查询部分
         */
        //得到查询参数
        String readerNamePara = pageReaderSearch.getReaderName();
        String readerAccountPara = pageReaderSearch.getReaderAccount();
        //如果查询参数不为"",则更改sql语句，同时将参数值存放到paraList当中去，以便后面预编译
        if (!readerNamePara.equals("")) {
            sql += " and readerName like ?";
            paraList.add("%"+readerNamePara+"%");
        }
        if (!readerAccountPara.equals("")) {
            sql += " and readerAccount like ?";
            paraList.add("%"+readerAccountPara+"%");
        }

        /**
         * 分页部分
         */
        sql += " limit ?,?";
        int numBegin = (currentPage - 1) * currentCount;
        int numEnd = currentCount;
        paraList.add(numBegin);
        paraList.add(numEnd);

        /**
         * 开始预编译
         */
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int i = 1;
        for (Object object : paraList) {
            pstmt.setObject(i, object);
            i++;
        }
        /**
         * 获得数据集
         */
        ResultSet rs = pstmt.executeQuery();
        /**
         * 存放到list中
         */
        while (rs.next()){
            String readerId = rs.getString("readerId");
            String readerName = rs.getString("readerName");
            String readerAccount = rs.getString("readerAccount");
            String readerPassword = rs.getString("readerPassword");
            int readerAuthorty = rs.getInt("readerAuthorty");
            Reader reader = new Reader(readerId, readerName, readerAccount, readerPassword, readerAuthorty);
            list.add(reader);
        }
        return list;
    }

    /**
     * 根据搜索条件查询总数目
     * @param pageReaderSearch
     * @return
     */
    @Override
    public int countSearchReaders(PageReaderSearch pageReaderSearch) throws SQLException {
        //创建数据库连接
        Connection connection = JDBCutils.getConnection();
        //书写初始sql语句
        String sql = "select * from t_reader where 1=1";
        //创建paraList来存放？数据
        List paraList = new ArrayList();
        /**
         * 查询部分
         */
        //得到查询参数
        String readerNamePara = pageReaderSearch.getReaderName();
        String readerAccountPara = pageReaderSearch.getReaderAccount();
        //如果查询参数不为"",则更改sql语句，同时将参数值存放到paraList当中去，以便后面预编译
        if (!readerNamePara.equals("")) {
            sql += " and readerName like ?";
            paraList.add("%"+readerNamePara+"%");
        }
        if (!readerAccountPara.equals("")) {
            sql += " and readerAccount like ?";
            paraList.add("%"+readerAccountPara+"%");
        }
        /**
         * 开始预编译
         */
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int i = 1;
        for (Object object : paraList) {
            pstmt.setObject(i, object);
            i++;
        }
        /**
         * 获得数据集
         */
        ResultSet rs = pstmt.executeQuery();
        /**
         * 计数
         */
        int totalCount=0;
        while (rs.next()){
            totalCount++;
        }
        return totalCount;
    }
}
