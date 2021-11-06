package com.qy.service;

import com.qy.pojo.Member;

import java.util.List;

/**
 * 用户类服务接口
 */
public interface MemberService {

    // 添加用户
    void add(Member member);

    // 通过电话查询用户
    Member findByTelephone(String telephone);

    // 通过月份查询用户
    List<Integer> findMemberCountByMonth(List<String> month);
}
