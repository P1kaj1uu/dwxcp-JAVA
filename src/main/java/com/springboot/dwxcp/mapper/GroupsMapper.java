package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Groups;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupsMapper extends BaseMapper<Groups> {
    List<Groups> getGroupsList(
            @Param("party") String party,
            @Param("name") String name,
            @Param("name1") String name1,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    boolean addGroups(Groups groups);

    boolean deleteGroupsById(int id);

    boolean editGroupsById(Groups groups);

    boolean batchAddGroups(@Param("list") List<Groups> groupsList);
}
