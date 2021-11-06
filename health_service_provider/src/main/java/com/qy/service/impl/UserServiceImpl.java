package com.qy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qy.dao.PermissionDao;
import com.qy.dao.RoleDao;
import com.qy.dao.UserDao;
import com.qy.pojo.Permission;
import com.qy.pojo.Role;
import com.qy.pojo.User;
import com.qy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 用户服务
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    // 根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username); // 查询用户基本信息，不包含用户角色
        if (user == null){
            return null;
        }

        Integer userId = user.getId();
        // 根据用户id查询对应的角色
        Set<Role> roles = roleDao.findByUserId(userId);
        for (Role role : roles){
            Integer roleId = role.getId();
            // 根据角色id查询关联权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            if(permissions != null && permissions.size() > 0){
                role.setPermissions(permissions);
            }
        }

        user.setRoles(roles);

        return user;
    }



}
