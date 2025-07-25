package com.takeout.merchant.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品评价实体
 */
@Data
public class ProductReview {
    /**
     * 评价ID
     */
    private Long id;
    
    /**
     * 商品ID
     */
    private Long productId;
    
    /**
     * 评价用户ID
     */
    private Long userId;
    
    /**
     * 评分（1~5）
     */
    private Integer rating;
    
    /**
     * 评价内容
     */
    private String comment;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 