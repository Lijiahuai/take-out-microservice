package com.takeout.user.service.impl;

import com.takeout.common.dto.Result;
import com.takeout.user.entity.UserInfo;
import com.takeout.user.mapper.UserInfoMapper;
import com.takeout.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户信息服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;
    
    @Override
    public Result<UserInfo> getUserInfo(Long userId) {
        UserInfo userInfo = userInfoMapper.getUserInfoByUserId(userId);
        
        // 如果用户信息不存在，则创建默认用户信息
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setNickname("用户" + userId);
            userInfo.setAvatar("https://example.com/default-avatar.png");
            userInfo.setBalance(BigDecimal.ZERO);
            userInfo.setGender(0);
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setUpdateTime(LocalDateTime.now());
            
            userInfoMapper.createUserInfo(userInfo);
        }
        
        return Result.success(userInfo);
    }

    @Override
    public Result<Void> updateUserInfo(UserInfo userInfo) {
        int result = userInfoMapper.updateUserInfo(userInfo);
        
        if (result > 0) {
            return Result.success("更新成功", null);
        } else {
            return Result.error("更新失败");
        }
    }
} 