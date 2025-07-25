package com.takeout.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单项DTO
 */
@Data
public class OrderItemDTO {
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
} 