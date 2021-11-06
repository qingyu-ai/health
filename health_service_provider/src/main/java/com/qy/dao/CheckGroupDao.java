package com.qy.dao;

import com.github.pagehelper.Page;
import com.qy.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * 检查组持久层Dao接口
 */
public interface CheckGroupDao {

    // 插入检查组
    void add(CheckGroup checkGroup);

    // 插入检查组与检查项多对多关联
    void setCheckGroupAndCheckItem(Map map);

    // 分页查询
    Page<CheckGroup> selectByCondition(String queryString);

    // 根据id查询检查组
    CheckGroup findById(Integer id);

    // 根据检查组id查询关联检查项
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 删除原先检查组与检查项关联数据
    void deleteAssociation(Integer id);

    // 修改（更新）检查组
    void edit(CheckGroup checkGroup);

    // 查询所有检查组
    List<CheckGroup> findAll();

}
