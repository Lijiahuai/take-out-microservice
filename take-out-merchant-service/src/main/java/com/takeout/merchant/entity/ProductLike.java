package com.takeout.merchant.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品点赞实体
 */
@Data
public class ProductLike {
    /**
     * 点赞ID
     */
    private Long id;
    
    /**
     * 商品ID
     */
    private Long productId;
    
    /**
     * 点赞用户ID
     */
    private Long userId;
    
    /**
     * 点赞时间
     */
    private LocalDateTime createTime;
} 