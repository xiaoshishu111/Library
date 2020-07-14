package com.daoimpl;

import com.dao.IUserDao;
import com.domain.User;
import com.util.JDBCutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public List<User> findAll() throws Exception{
        List<User> list=new ArrayList<>();
        //加载jar包
        //加载驱动
       //Class.forName("com.mysql.jdbc.Driver");
        //创建连接
       //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
       //创建执行对象
        Statement statement=connection.createStatement();
        //输入sql语句
        String sql="select * from t_user";
        //执行sql语句
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            String id  = rs.getString("id");
            String name = rs.getString("name");

            // 输出数据
//            System.out.print(String.format("ID: %s ",id));
//            System.out.println(String.format("NAME: %s ",name));
            //添加到对象中
            User user=new User();
            user.setId(id);
            user.setName(name);
            list.add(user);

        }
        // 完成后释放资源
//        rs.close();
//        statement.close();
//        connection.close();
        JDBCutils.close(connection,statement,rs);
        return list;
    }


    @Override
    public void save(User user) throws Exception{

        //加载jar包
        //加载驱动
//        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="insert into t_user values (?,?)";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,user.getId());
        pstmt.setObject(2,user.getName());
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,null);

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) throws Exception{
        //加载jar包
        //加载驱动
//        Class.forName("com.mysql.jdbc.Driver");
        //创建连接
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_books?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","111");
        Connection connection = JDBCutils.getConnection();
        //输入sql语句
        String sql="delete from t_user where id=?";
        //创建预处理执行对象
        PreparedStatement pstmt=connection.prepareStatement(sql);
        //给？赋值
        pstmt.setObject(1,id);
        //执行sql语句
        pstmt.executeUpdate();
        //释放资源
//        pstmt.close();
//        connection.close();
        JDBCutils.close(connection,pstmt,null);
    }
}
