package com.takeout.auth.service.impl;

import com.takeout.auth.dto.LoginRequest;
import com.takeout.auth.dto.LoginResponse;
import com.takeout.auth.dto.RegisterRequest;
import com.takeout.auth.entity.Admin;
import com.takeout.auth.entity.User;
import com.takeout.auth.mapper.AuthMapper;
import com.takeout.auth.service.AuthService;
import com.takeout.common.constant.RedisKey;
import com.takeout.common.dto.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    
    // JWT密钥
    private static final String JWT_SECRET_KEY = "takeoutSecretKey12345678901234567890123456789012";
    // token过期时间（秒）
    private static final long TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60;
    
    @Override
    public Result<LoginResponse> login(LoginRequest loginRequest) {
        User user = authMapper.getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            return Result.error("用户不存在");
        }
        
        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(
                loginRequest.getPassword().getBytes(StandardCharsets.UTF_8));
        
        if (!user.getPassword().equals(encryptPassword)) {
            return Result.error("密码错误");
        }
        
        // 生成token
        String token = generateToken(user.getId().toString());
        
        // 将token存入Redis
        String redisKey = RedisKey.USER_TOKEN_PREFIX + user.getId();
        redisTemplate.opsForValue().set(redisKey, token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setToken(token);
        response.setExpireTime(TOKEN_EXPIRE_TIME);
        
        return Result.success(response);
    }

    @Override
    public Result<LoginResponse> adminLogin(LoginRequest loginRequest) {
        Admin admin = authMapper.getAdminByUsername(loginRequest.getUsername());
        if (admin == null) {
            return Result.error("管理员不存在");
        }
        
        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(
                loginRequest.getPassword().getBytes(StandardCharsets.UTF_8));
        
        if (!admin.getPassword().equals(encryptPassword)) {
            return Result.error("密码错误");
        }
        
        // 生成token
        String token = generateToken(admin.getId().toString());
        
        // 将token存入Redis
        String redisKey = RedisKey.ADMIN_TOKEN_PREFIX + admin.getId();
        redisTemplate.opsForValue().set(redisKey, token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        
        LoginResponse response = new LoginResponse();
        response.setUserId(admin.getId());
        response.setUsername(admin.getUsername());
        response.setToken(token);
        response.setExpireTime(TOKEN_EXPIRE_TIME);
        
        return Result.success(response);
    }

    @Override
    public Result<Void> register(RegisterRequest registerRequest) {
        // 检查用户名是否已存在
        int count = authMapper.checkUsernameExists(registerRequest.getUsername());
        if (count > 0) {
            return Result.error("用户名已存在");
        }
        
        // 创建用户
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        
        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(
                registerRequest.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(encryptPassword);
        
        user.setPhone(registerRequest.getPhone());
        user.setEmail(registerRequest.getEmail());
        
        // 保存用户
        int result = authMapper.registerUser(user);
        if (result <= 0) {
            return Result.error("注册失败");
        }
        
        return Result.success("注册成功", null);
    }

    @Override
    public Result<Void> logout(Long userId) {
        // 从Redis中删除token
        String redisKey = RedisKey.USER_TOKEN_PREFIX + userId;
        Boolean result = redisTemplate.delete(redisKey);
        
        if (Boolean.TRUE.equals(result)) {
            return Result.success("退出成功", null);
        } else {
            return Result.error("退出失败");
        }
    }
    
    /**
     * 生成JWT Token
     * @param subject 主题（用户ID）
     * @return token
     */
    private String generateToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_EXPIRE_TIME * 1000);
        
        SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET_KEY), "HmacSHA256");
        
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
} 