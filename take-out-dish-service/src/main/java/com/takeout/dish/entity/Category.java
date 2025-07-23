package com.takeout.dish.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜品分类实体
 */
@Data
public class Category {
    /**
     * 分类ID
     */
    private Long id;
    
    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 分类图标
     */
    private String icon;
    
    /**
     * 分类排序
     */
    private Integer sort;
    
    /**
     * 分类状态（0-禁用，1-启用）
     */
    private Integer status;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 