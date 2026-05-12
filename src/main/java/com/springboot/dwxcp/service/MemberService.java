package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {
    // 查询党员列表
    List<Member> getMemberList(
            @Param("name") String name,
            @Param("condition") String condition,
            @Param("groups") String groups,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 新增党员
    boolean addMember(Member member);

    // 删除党员
    boolean deleteMemberById(int id);

    // 编辑党员
    boolean editMemberById(Member member);

    // 批量新增党员
    boolean batchAddMember(@Param("list") List<Member> memberList);
}
