package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper extends BaseMapper<Member> {
    List<Member> getMemberList(
            @Param("name") String name,
            @Param("condition") String condition,
            @Param("groups") String groups
    );

    boolean addMember(Member member);

    boolean deleteMemberById(int id);

    boolean editMemberById(Member member);

    boolean batchAddMember(@Param("list") List<Member> memberList);
}
