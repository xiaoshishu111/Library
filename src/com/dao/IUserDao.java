package com.dao;

import com.domain.User;

import java.util.List;

public interface IUserDao {
    //查询
    List<User> findAll() throws Exception;
    //增加
    void save(User user) throws Exception;
    //修改
    void update(User user);
    //删除
    void delete(String id) throws Exception;
}
