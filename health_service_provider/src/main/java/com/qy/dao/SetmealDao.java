package com.qy.dao;

import com.github.pagehelper.Page;
import com.qy.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * 套餐类持久层Dao
 */
public interface SetmealDao {

    // 添加套餐
    void add(Setmeal setmeal);

    // 绑定套餐和检查组多对多关系
    void setSetmealAndCheckGroup(Map<String, Integer> map);

    // 分页查询
    Page<Setmeal> findByCondition(String queryString);

    // 查询套餐所有图片
    List<Setmeal> findAll();

    // 通过id查询套餐详情
    Setmeal findById(int id);

    // 查询套餐总数
    List<Map<String,Object>> findSetmealCount();

}