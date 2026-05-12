package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.User;
import com.springboot.dwxcp.mapper.UserMapper;
import com.springboot.dwxcp.service.UserService;
import com.springboot.dwxcp.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList(int pageNum, int pageSize) {
        return userMapper.selectUserList(pageNum, pageSize);
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

    @Override
    public User selectUserLogin(User user) {
        return userMapper.selectUserLogin(user);
    }
}
