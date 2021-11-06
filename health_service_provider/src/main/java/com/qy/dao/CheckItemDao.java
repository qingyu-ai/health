package com.qy.dao;

import com.github.pagehelper.Page;
import com.qy.pojo.CheckItem;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {

    // ============================检查项相关============================================================

    // 检查项目添加进数据库
    void add(CheckItem checkItem);

    // 查询检查项数据
    Page<CheckItem> selectByCondition(String queryString);

    // 查询检查项与检查组是否关联，有关联（即查询到数据，条数大于0）则不能删除检查项
    long findCountByCheckItemId(Integer checkItemId);

    // 根据id删除检查项
    void deleteById(Integer id);

    // 编辑（更新）检查项
    void edit(CheckItem checkItem);

    // 根据id查询检查项
    CheckItem findById(Integer id);

    // ============================检查项相关============================================================

    // 查询所有检查项
    List<CheckItem> findAll();


}
