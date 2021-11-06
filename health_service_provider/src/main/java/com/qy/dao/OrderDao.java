package com.qy.dao;

import com.qy.pojo.Order;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    // 添加预约信息
    void add(Order order);

    // 条件查询
    List<Order> findByCondition(Order order);

    // 通过id查询信息
    Map findById4Detail(Integer id);

    // 通过日期查询预约数
    Integer findOrderCountByDate(String date);

    // 查询日期之后的预约数
    Integer findOrderCountAfterDate(String date);

    // 通过日期查询到诊数
    Integer findVisitsCountByDate(String date);

    // 查询日期之后的到诊数
    Integer findVisitsCountAfterDate(String date);

    // 热门套餐
    List<Map> findHotSetmeal();

}
