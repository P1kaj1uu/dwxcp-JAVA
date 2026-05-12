package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.User;
import com.springboot.dwxcp.service.UserService;
import com.springboot.dwxcp.util.JWTUtil;
import com.springboot.dwxcp.util.PasswordEncoderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户列表")
    @GetMapping(value = "/list")
    private BaseResponse selectUserList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        System.out.println("----------------查询所有用户列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<User> list = null;
        PageInfo page = null;
        try {
            list = userService.selectUserList(pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("根据用户id查询当前用户信息")
    @GetMapping(value = "/find")
    private BaseResponse selectMyUser(@RequestParam("userId") int userId) {
        System.out.println("----------------根据用户id查询当前用户信息------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        User list = null;
        try {
            list = userService.selectMyUser(userId);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(list);
        return response;
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/editPassword")
    private BaseResponse editUserPasswordById(@RequestBody User user) {
        System.out.println("----------------修改密码------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            // 先检查用户名是否已存在
            User existingUser = userService.selectInfoByName(user.getUsername());
            if (existingUser == null) {
                return new BaseResponse(StatusCode.Fail.getCode(), "该用户不存在，请先注册");
            }
            flag = userService.editUserPasswordById(user);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "修改密码失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("注销用户")
    @DeleteMapping(value = "/delete")
    private BaseResponse deleteUserById(@RequestParam("userId") int userId) {
        System.out.println("----------------根据用户id删除注销------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = userService.deleteUserById(userId);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "注销用户失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("注册用户")
    @PostMapping(value = "/register")
    private BaseResponse addUser(@RequestBody User user) {
        System.out.println("----------------注册用户------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            // 先检查用户名是否已存在
            User existingUser = userService.selectInfoByName(user.getUsername());
            if (existingUser != null) {
                return new BaseResponse(StatusCode.Fail.getCode(), "该账号已注册，请使用其他账号");
            }
            flag = userService.addUser(user);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "注册用户失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    private BaseResponse selectUserLogin(@RequestBody User user) {
        System.out.println("----------------登录------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        User currentUser = null;
        User tempUser = null;
        String token = "";
        boolean flag = true;
        Map userMap = new HashMap<>();
        try {
            String oldPassword = user.getPassword();
            tempUser = userService.selectInfoByName(user.getUsername());
            if (tempUser != null) {
                flag = PasswordEncoderUtil.matches(oldPassword, tempUser.getPassword());
                user.setPassword(tempUser.getPassword());
                System.out.println("输入的密码：" + oldPassword);
                System.out.println(flag);
            }
            currentUser = userService.selectUserLogin(user);
            if (currentUser == null || !flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "账号或密码错误");
            } else {
                // 生成token并返回
                int id = currentUser.getId().intValue();
                String name = currentUser.getUsername();
                long curId = new Long((long)id);
                token = JWTUtil.createToken(curId, name);
                userMap.put("id", currentUser.getId());
                userMap.put("username", currentUser.getUsername());
                userMap.put("token", token);
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(userMap);
        return response;
    }
}
