package com.takeout.user.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车项实体
 */
@Data
public class CartItem {
    /**
     * 购物车项ID
     */
    private Long id;
    
    /**
     * 购物车ID
     */
    private Long cartId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 菜品ID
     */
    private Long dishId;
    
    /**
     * 菜品名称
     */
    private String dishName;
    
    /**
     * 菜品图片
     */
    private String dishImage;
    
    /**
     * 菜品单价
     */
    private BigDecimal price;
    
    /**
     * 购买数量
     */
    private Integer quantity;
    
    /**
     * 小计金额
     */
    private BigDecimal subtotal;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 