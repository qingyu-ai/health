package com.qy.service;


import com.qy.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    // 批量导入数据
    void add(List<OrderSetting> data);

    // 根据日期获取数据
    List<Map> getOrderSettingByMonth(String date);

    // 通过日期修改数据
    void editNumberByDate(OrderSetting orderSetting);

}
