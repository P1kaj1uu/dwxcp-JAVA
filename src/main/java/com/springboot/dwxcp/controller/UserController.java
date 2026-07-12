package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.User;
import com.springboot.dwxcp.service.UserService;
import com.springboot.dwxcp.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户列表")
    @GetMapping(value = "/list")
    private BaseResponse selectUserList(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<User> page = userService.selectUserList(pageNum, pageSize);
        return new BaseResponse<>(StatusCode.Success, page);
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
        String username = user.getUsername();
        String rawPassword = user.getPassword();
        if (username == null || rawPassword == null) {
            return new BaseResponse(StatusCode.InvalidParams.getCode(), "用户名或密码不能为空");
        }
        User currentUser = userService.selectUserLogin(username, rawPassword);
        if (currentUser == null) {
            return new BaseResponse(StatusCode.AccountPasswordNotMatch);
        }
        // 生成 token
        long curId = currentUser.getId();
        String token = JWTUtil.createToken(curId, currentUser.getUsername());
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", currentUser.getId());
        userMap.put("username", currentUser.getUsername());
        userMap.put("token", token);
        return new BaseResponse<>(StatusCode.Success, userMap);
    }
}
