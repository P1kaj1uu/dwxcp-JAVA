package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Groups;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupsService {
    // 查询党小组列表
    List<Groups> getGroupsList(
            @Param("party") String party,
            @Param("name") String name,
            @Param("name1") String name1,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 新增党小组
    boolean addGroups(Groups groups);

    // 删除党小组
    boolean deleteGroupsById(int id);

    // 编辑党小组
    boolean editGroupsById(Groups groups);

    // 批量新增党小组
    boolean batchAddGroups(@Param("list") List<Groups> groupsList);
}
