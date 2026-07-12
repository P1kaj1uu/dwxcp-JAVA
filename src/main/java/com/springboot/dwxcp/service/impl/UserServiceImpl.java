package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.User;
import com.springboot.dwxcp.mapper.UserMapper;
import com.springboot.dwxcp.service.UserService;
import com.springboot.dwxcp.util.PageUtil;
import com.springboot.dwxcp.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> selectUserList(int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<User> list = userMapper.selectUserList();
        return new PageInfo<>(list);
    }

    @Override
    public User selectMyUser(int userId) {
        return userMapper.selectMyUser(userId);
    }

    @Override
    public User selectInfoByName(String username) {
        return userMapper.selectInfoByName(username);
    }

    @Override
    public boolean editUserPasswordById(User user) {
        String encodedPassword = PasswordEncoderUtil.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userMapper.editUserPasswordById(user);
    }

    @Override
    public boolean deleteUserById(int userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public boolean addUser(User user) {
        // 加密密码
        String encodedPassword = PasswordEncoderUtil.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userMapper.addUser(user);
    }

    /**
     * 登录：先按用户名查（含密码字段），再 BCrypt 比对
     * 修复点：原 SQL 用明文比对密码，与 PasswordEncoderUtil.encode() 写入的 BCrypt 哈希不匹配，登录永远失败
     */
    @Override
    public User selectUserLogin(String username, String rawPassword) {
        if (username == null || rawPassword == null) {
            return null;
        }
        User stored = userMapper.selectInfoByName(username);
        if (stored == null || stored.getPassword() == null) {
            return null;
        }
        if (!PasswordEncoderUtil.matches(rawPassword, stored.getPassword())) {
            return null;
        }
        // 不让密码字段外泄
        stored.setPassword(null);
        return stored;
    }
}
