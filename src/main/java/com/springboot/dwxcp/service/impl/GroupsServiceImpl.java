package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.Groups;
import com.springboot.dwxcp.mapper.GroupsMapper;
import com.springboot.dwxcp.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {
    @Autowired
    private GroupsMapper  groupsMapper;

    @Override
    public List<Groups> getGroupsList(String party, String name, String name1, int pageNum, int pageSize) {
        return groupsMapper.getGroupsList(party, name, name1, pageNum, pageSize);
    }

    @Override
    public boolean addGroups(Groups groups) {
        return groupsMapper.addGroups(groups);
    }

    @Override
    public boolean deleteGroupsById(int id) {
        return groupsMapper.deleteGroupsById(id);
    }

    @Override
    public boolean editGroupsById(Groups groups) {
        return groupsMapper.editGroupsById(groups);
    }

    @Override
    public boolean batchAddGroups(List<Groups> groupsList) {
        return groupsMapper.batchAddGroups(groupsList);
    }
}
