package com.takeout.merchant.entity;

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
     * 商家用户名（登录账号）
     */
    private String username;
    
    /**
     * 商家密码
     */
    private String password;
    
    /**
     * 商家姓名
     */
    private String name;
    
    /**
     * 商家电话
     */
    private String phone;
    
    /**
     * 商家邮箱
     */
    private String email;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 状态（0-禁用，1-启用）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 