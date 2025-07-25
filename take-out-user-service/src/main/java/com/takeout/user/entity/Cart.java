package com.takeout.user.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 购物车实体
 */
@Data
public class Cart {
    /**
     * 购物车ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 店铺名称
     */
    private String shopName;
    
    /**
     * 商品总数量
     */
    private Integer totalQuantity;
    
    /**
     * 商品总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 购物车项列表（非数据库字段）
     */
    private transient List<CartItem> cartItems;
} 