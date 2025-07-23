package com.takeout.merchant.dto;

import lombok.Data;

/**
 * 店铺数据传输对象
 */
@Data
public class ShopDTO {
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
} 