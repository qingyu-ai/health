package com.qy.dao;

import com.qy.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {

    // 插入预约信息
    void add(OrderSetting orderSetting);

    // 根据日期更新可预约人数
    void editNumberByOrderDate(OrderSetting orderSetting);

    // 通过日期查询数据
    long findCountByOrderDate(Date orderDate);

    // 根据日期范围查询预约设置信息
    List<OrderSetting> getOrderSettingByMonth(Map map);


    //更新已预约人数
    void editReservationsByOrderDate(OrderSetting orderSetting);

    //根据预约日期查询预约设置信息
    OrderSetting findByOrderDate(Date orderDate);
}
