package cn.base.java;

import com.daoimpl.UserDao;
import com.domain.User;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        //查询所有数据
        List<User> users=new UserDao().findAll();
        for (User user:users){
            System.out.println(user);
        }

        //插入一条数据
        //初始化对象
        User user1=new User("1234578","xiaoliu");
        //执行sql
        new UserDao().save(user1);

        //删除一条数据
        new UserDao().delete("1");



    }
}
