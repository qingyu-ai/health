package com.qy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qy.dao.MemberDao;
import com.qy.pojo.Member;
import com.qy.service.MemberService;
import com.qy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    //新增会员
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    //根据手机号查询会员
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    // 通过月份查询用户
    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> memberCount  = new ArrayList<>();
        for(String month : months){
            month = month + "-31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(month);
            memberCount .add(count);
        }
        return memberCount;
    }

}
