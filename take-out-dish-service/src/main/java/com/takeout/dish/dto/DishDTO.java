package com.takeout.dish.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 菜品数据传输对象
 */
@Data
public class DishDTO {
    /**
     * 菜品ID
     */
    private Long id;
    
    /**
     * 菜品名称
     */
    private String name;
    
    /**
     * 菜品描述
     */
    private String description;
    
    /**
     * 菜品图片
     */
    private String image;
    
    /**
     * 菜品价格
     */
    private BigDecimal price;
    
    /**
     * 菜品状态（0-下架，1-上架）
     */
    private Integer status;
    
    /**
     * 菜品分类ID
     */
    private Long categoryId;
    
    /**
     * 店铺ID
     */
    private Long shopId;
} 