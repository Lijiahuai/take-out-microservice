package com.takeout.dish.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品视图对象
 */
@Data
public class DishVO {
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
     * 菜品分类名称
     */
    private String categoryName;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 店铺名称
     */
    private String shopName;
    
    /**
     * 销量
     */
    private Integer sales;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
} 