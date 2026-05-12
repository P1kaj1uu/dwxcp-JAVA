package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Branchs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BranchsMapper extends BaseMapper<Branchs> {
    List<Branchs> getBranchsList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    boolean addBranchs(Branchs branchs);

    boolean deleteBranchsById(int id);

    boolean editBranchsById(Branchs branchs);

    boolean batchAddBranchs(@Param("list") List<Branchs> branchsList);
}
