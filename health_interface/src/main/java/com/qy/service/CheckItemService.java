package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.CheckItem;

import java.util.List;

// 服务接口
public interface CheckItemService {

    // 测试方法
    String check(String msg);


    // ============================检查项相关============================================================

    // 添加检查项
    void add(CheckItem checkItem);

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    // 删除检查项
    void deleteById(Integer id);

    // 编辑检查项
    void edit(CheckItem checkItem);

    // 根据id查询检查项
    CheckItem findById(Integer id);


    // ============================检查组相关============================================================

    // 查询所有检查项
    List<CheckItem> findAll();

}
