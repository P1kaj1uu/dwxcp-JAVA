package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserService {
    // 查询用户列表
    List<User> selectUserList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 根据用户id查询信息
    User selectMyUser(int userId);

    // 根据用户账号查询信息
    User selectInfoByName(String username);

    // 修改密码
    boolean editUserPasswordById(User user);

    // 注销用户
    boolean deleteUserById(int userId);

    // 注册用户
    boolean addUser(User user);

    // 登录
    User selectUserLogin(User user);
}
