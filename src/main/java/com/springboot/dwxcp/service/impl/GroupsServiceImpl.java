package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.Groups;
import com.springboot.dwxcp.mapper.GroupsMapper;
import com.springboot.dwxcp.service.GroupsService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {
    @Autowired
    private GroupsMapper  groupsMapper;

    @Override
    public PageInfo<Groups> getGroupsList(String party, String name, String name1, int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<Groups> list = groupsMapper.getGroupsList(party, name, name1);
        return new PageInfo<>(list);
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
