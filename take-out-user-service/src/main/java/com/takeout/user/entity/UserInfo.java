package com.takeout.user.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户信息实体
 */
@Data
public class UserInfo {
    /**
     * 信息ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别：M/F/O
     */
    private String gender;

    /**
     * 模拟坐标X(范围1-10000)
     */
    private Integer x;

    /**
     * 模拟坐标Y(范围1-10000)
     */
    private Integer y;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 余额
     */
    private BigDecimal balance;
    
    /**
     * 生日
     */
    private LocalDateTime birthday;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 