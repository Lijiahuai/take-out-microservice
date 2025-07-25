package com.takeout.auth.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商家实体
 */
@Data
public class Merchant {
    /**
     * 商家ID
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 