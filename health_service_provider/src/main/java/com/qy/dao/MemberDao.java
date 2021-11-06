package com.qy.dao;

import com.github.pagehelper.Page;
import com.qy.pojo.Member;

import java.util.List;

public interface MemberDao {

    // 查询所有用户
    List<Member> findAll();

    // 通过条件查询用户
    Page<Member> selectByCondition(String queryString);

    // 添加用户用户
    void add(Member member);

    // 通过id删除用户
    void deleteById(Integer id);

    // 通过id查询用户
    Member findById(Integer id);

    // 通过电话查询用户
    Member findByTelephone(String telephone);

    // 修改更新用户
    void edit(Member member);

    // 查询指定日期之前用户
    Integer findMemberCountBeforeDate(String date);

    // 通过日期查询用户
    Integer findMemberCountByDate(String date);

    // 查询指定日期之后用户
    Integer findMemberCountAfterDate(String date);

    // 查询用户总数
    Integer findMemberTotalCount();

}