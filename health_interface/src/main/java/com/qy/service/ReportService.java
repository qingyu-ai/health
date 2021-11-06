package com.qy.service;

import java.util.Map;

public interface ReportService {

    // 运营数据统计
    Map<String,Object> getBusinessReportData() throws Exception;
}
