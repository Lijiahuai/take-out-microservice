package com.takeout.merchant.dto;

import lombok.Data;

/**
 * 分类数据传输对象
 */
@Data
public class CategoryDTO {
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
} 