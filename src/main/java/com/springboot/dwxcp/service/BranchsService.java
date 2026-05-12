package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Branchs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BranchsService {
    // 查询党支部列表
    List<Branchs> getBranchsList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 新增党支部
    boolean addBranchs(Branchs branchs);

    // 删除党支部
    boolean deleteBranchsById(int id);

    // 编辑党支部
    boolean editBranchsById(Branchs branchs);

    // 批量新增党支部
    boolean batchAddBranchs(@Param("list") List<Branchs> branchsList);
}
