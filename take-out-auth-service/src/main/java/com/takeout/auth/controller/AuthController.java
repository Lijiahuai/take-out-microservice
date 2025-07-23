package com.takeout.auth.controller;

import com.takeout.auth.dto.LoginRequest;
import com.takeout.auth.dto.LoginResponse;
import com.takeout.auth.dto.RegisterRequest;
import com.takeout.auth.service.AuthService;
import com.takeout.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    
    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
    
    /**
     * 管理员登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @PostMapping("/admin/login")
    public Result<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        return authService.adminLogin(loginRequest);
    }
    
    /**
     * 用户注册
     * @param registerRequest 注册请求
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    
    /**
     * 退出登录
     * @param userId 用户ID
     * @return 退出结果
     */
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("X-User-Id") Long userId) {
        return authService.logout(userId);
    }
} 