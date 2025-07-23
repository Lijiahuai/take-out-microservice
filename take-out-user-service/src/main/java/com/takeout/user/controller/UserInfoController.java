package com.takeout.user.controller;

import com.takeout.common.dto.Result;
import com.takeout.user.entity.UserInfo;
import com.takeout.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息控制器
 */
@RestController
@RequestMapping("/user/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping
    public Result<UserInfo> getUserInfo(@RequestHeader("X-User-Id") Long userId) {
        return userInfoService.getUserInfo(userId);
    }
    
    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @param userId 用户ID
     * @return 更新结果
     */
    @PutMapping
    public Result<Void> updateUserInfo(@RequestBody UserInfo userInfo, @RequestHeader("X-User-Id") Long userId) {
        userInfo.setUserId(userId);
        return userInfoService.updateUserInfo(userInfo);
    }
} 