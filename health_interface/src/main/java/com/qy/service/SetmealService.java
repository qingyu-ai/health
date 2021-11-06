package com.qy.service;

import com.qy.entity.PageResult;
import com.qy.entity.QueryPageBean;
import com.qy.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * 体检套餐服务接口
 */
public interface SetmealService {

    // 新增套餐
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    // 分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    // 查询所有图片名称
    List<Setmeal> findAll();

    // 通过id查询套餐详情
    Setmeal findById(int id);

    // 查询套餐总数
    List<Map<String,Object>> findSetmealCount();


}