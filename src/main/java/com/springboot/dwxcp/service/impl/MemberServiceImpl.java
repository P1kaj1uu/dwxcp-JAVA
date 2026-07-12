package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.Member;
import com.springboot.dwxcp.mapper.MemberMapper;
import com.springboot.dwxcp.service.MemberService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageInfo<Member> getMemberList(String name, String condition, String groups, int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<Member> list = memberMapper.getMemberList(name, condition, groups);
        return new PageInfo<>(list);
    }

    @Override
    public boolean addMember(Member member) {
        return memberMapper.addMember(member);
    }

    @Override
    public boolean deleteMemberById(int id) {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public boolean editMemberById(Member member) {
        return memberMapper.editMemberById(member);
    }

    @Override
    public boolean batchAddMember(List<Member> memberList) {
        return memberMapper.batchAddMember(memberList);
    }
}
