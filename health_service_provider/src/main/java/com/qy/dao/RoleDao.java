package com.qy.dao;

import com.qy.pojo.Role;

import java.util.Set;

/**
 * 角色
 */
public interface RoleDao {

    // 提供id查询角色
    Set<Role> findByUserId(Integer userId);

}
