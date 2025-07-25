package com.takeout.merchant.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存实体
 */
@Data
public class Inventory {
    /**
     * 库存记录ID
     */
    private Long id;
    
    /**
     * 对应商品ID
     */
    private Long productId;
    
    /**
     * 库存数量
     */
    private Integer quantity;
    
    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdated;
} 