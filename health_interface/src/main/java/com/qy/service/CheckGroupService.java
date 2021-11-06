package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.CheckGroup;

import java.util.List;

/**
 * 检查组服务接口
 */
public interface CheckGroupService {

    // 新增检查组
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    // 根据id查询检查组
    CheckGroup findById(Integer id);

    // 根据检查组id查询关联的检查项
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    // 编辑检查组，同时需要更新和检查项的关联关系
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    // 查询所有检查组
    List<CheckGroup> findAll();
}
