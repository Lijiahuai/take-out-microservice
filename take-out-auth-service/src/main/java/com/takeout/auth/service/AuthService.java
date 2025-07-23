package com.takeout.auth.service;

import com.takeout.auth.dto.LoginRequest;
import com.takeout.auth.dto.LoginResponse;
import com.takeout.auth.dto.RegisterRequest;
import com.takeout.auth.entity.User;
import com.takeout.common.dto.Result;

/**
 * 认证服务接口
 */
public interface AuthService {
    
    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    Result<LoginResponse> login(LoginRequest loginRequest);
    
    /**
     * 管理员登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    Result<LoginResponse> adminLogin(LoginRequest loginRequest);
    
    /**
     * 用户注册
     * @param registerRequest 注册请求
     * @return 注册结果
     */
    Result<Void> register(RegisterRequest registerRequest);
    
    /**
     * 退出登录
     * @param userId 用户ID
     * @return 退出结果
     */
    Result<Void> logout(Long userId);
} 