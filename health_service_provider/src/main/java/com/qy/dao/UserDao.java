package com.qy.dao;

import com.qy.pojo.User;

/**
 * 用户
 */
public interface UserDao {

    // 根据用户名查询数据库获取用户信息和关联的角色信息
    User findByUsername(String username);


}
