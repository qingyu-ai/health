package com.qy.dao;

import com.qy.pojo.Permission;

import java.util.Set;

/**
 * 权限
 */
public interface PermissionDao {

    // 提供角色id查询权限
    Set<Permission> findByRoleId(int roleId);

}
