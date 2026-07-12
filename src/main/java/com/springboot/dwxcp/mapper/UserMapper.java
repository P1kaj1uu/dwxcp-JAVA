package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserList();

    User selectMyUser(int userId);

    User selectInfoByName(String username);

    boolean editUserPasswordById(User user);

    boolean deleteUserById(int userId);

    boolean addUser(User user);
}
