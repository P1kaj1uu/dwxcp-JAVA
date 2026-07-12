package com.springboot.dwxcp.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    // 查询用户列表（分页）
    PageInfo<User> selectUserList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 根据用户id查询信息
    User selectMyUser(int userId);

    // 根据用户账号查询信息（内部使用，含密码字段）
    User selectInfoByName(String username);

    // 修改密码
    boolean editUserPasswordById(User user);

    // 注销用户
    boolean deleteUserById(int userId);

    // 注册用户
    boolean addUser(User user);

    // 登录：返回 user；null 表示用户名不存在；user 非空但密码不匹配由 Controller 处理
    User selectUserLogin(String username, String rawPassword);
}
