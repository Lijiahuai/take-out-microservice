package com.takeout.merchant.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店铺视图对象
 */
@Data
public class ShopVO {
    /**
     * 店铺ID
     */
    private Long id;
    
    /**
     * 店铺名称
     */
    private String name;
    
    /**
     * 店铺图片
     */
    private String image;
    
    /**
     * 店铺介绍
     */
    private String introduction;
    
    /**
     * 店铺地址
     */
    private String address;
    
    /**
     * 店铺电话
     */
    private String phone;
    
    /**
     * 店铺状态（0-休息中，1-营业中）
     */
    private Integer status;
    
    /**
     * 营业时间
     */
    private String openingHours;
    
    /**
     * 店铺评分
     */
    private Double rating;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
} 