package com.takeout.merchant.service;

import com.takeout.common.dto.Result;
import com.takeout.merchant.dto.CategoryDTO;
import com.takeout.merchant.entity.Category;

import java.util.List;

/**
 * 分类服务接口
 */
public interface CategoryService {
    
    /**
     * 获取店铺分类列表
     * @param shopId 店铺ID
     * @return 分类列表
     */
    Result<List<Category>> getCategoriesByShopId(Long shopId);
    
    /**
     * 获取分类详情
     * @param id 分类ID
     * @return 分类详情
     */
    Result<Category> getCategoryDetail(Long id);
    
    /**
     * 创建分类
     * @param categoryDTO 分类DTO
     * @return 创建结果
     */
    Result<Long> createCategory(CategoryDTO categoryDTO);
    
    /**
     * 更新分类
     * @param categoryDTO 分类DTO
     * @return 更新结果
     */
    Result<Void> updateCategory(CategoryDTO categoryDTO);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param shopId 店铺ID
     * @return 删除结果
     */
    Result<Void> deleteCategory(Long id, Long shopId);
} 