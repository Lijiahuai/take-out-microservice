package com.takeout.user.service;

import com.takeout.common.dto.Result;
import com.takeout.user.entity.UserInfo;

/**
 * 用户信息服务接口
 */
public interface UserInfoService {
    
    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    Result<UserInfo> getUserInfo(Long userId);
    
    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 更新结果
     */
    Result<Void> updateUserInfo(UserInfo userInfo);
} 