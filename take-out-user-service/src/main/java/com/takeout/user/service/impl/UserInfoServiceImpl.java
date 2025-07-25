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
            userInfo.setRealName("用户" + userId);
            userInfo.setAvatar("https://randomuser.me/api/portraits/men/1.jpg");
            userInfo.setAddress("中关村南大街");
            userInfo.setBalance(BigDecimal.ZERO);
            userInfo.setGender("O"); // O for Other/Unknown
            userInfo.setPhone("");
            userInfo.setX(5000); // 默认值设为中间值5000
            userInfo.setY(5000); // 默认值设为中间值5000
            userInfo.setRemark("");
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setUpdateTime(LocalDateTime.now());

            userInfoMapper.createUserInfo(userInfo);
        }

        return Result.success(userInfo);
    }

    @Override
    public Result<Void> updateUserInfo(UserInfo userInfo) {
        // 先获取原有信息
        UserInfo existingInfo = userInfoMapper.getUserInfoByUserId(userInfo.getUserId());
        if (existingInfo == null) {
            return Result.error("用户信息不存在");
        }
        
        // 只更新非null字段，保留原有值
        if (userInfo.getNickname() == null) {
            userInfo.setNickname(existingInfo.getNickname());
        }
        if (userInfo.getRealName() == null) {
            userInfo.setRealName(existingInfo.getRealName());
        }
        if (userInfo.getPhone() == null) {
            userInfo.setPhone(existingInfo.getPhone());
        }
        if (userInfo.getGender() == null) {
            userInfo.setGender(existingInfo.getGender());
        }
        if (userInfo.getAvatar() == null) {
            userInfo.setAvatar(existingInfo.getAvatar());
        }
        if (userInfo.getBalance() == null) {
            userInfo.setBalance(existingInfo.getBalance());
        }
        if (userInfo.getBirthday() == null) {
            userInfo.setBirthday(existingInfo.getBirthday());
        }
        if (userInfo.getAddress() == null) {
            userInfo.setAddress(existingInfo.getAddress());
        }
        if (userInfo.getX() == null) {
            userInfo.setX(existingInfo.getX());
        }
        if (userInfo.getY() == null) {
            userInfo.setY(existingInfo.getY());
        }
        if (userInfo.getRemark() == null) {
            userInfo.setRemark(existingInfo.getRemark());
        }
        
        int result = userInfoMapper.updateUserInfo(userInfo);
        
        if (result > 0) {
            return Result.success("更新成功", null);
        } else {
            return Result.error("更新失败");
        }
    }
} 