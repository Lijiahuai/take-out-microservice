package com.takeout.merchant.controller;

import com.takeout.common.dto.Result;
import com.takeout.merchant.dto.CategoryDTO;
import com.takeout.merchant.entity.Category;
import com.takeout.merchant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 */
@RestController
@RequestMapping("/merchant/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    /**
     * 获取店铺分类列表
     * @param shopId 店铺ID
     * @return 分类列表
     */
    @GetMapping("/shop/{shopId}")
    public Result<List<Category>> getCategoriesByShopId(@PathVariable Long shopId) {
        return categoryService.getCategoriesByShopId(shopId);
    }
    
    /**
     * 获取分类详情
     * @param id 分类ID
     * @return 分类详情
     */
    @GetMapping("/{id}")
    public Result<Category> getCategoryDetail(@PathVariable Long id) {
        return categoryService.getCategoryDetail(id);
    }
    
    /**
     * 创建分类
     * @param categoryDTO 分类DTO
     * @return 创建结果
     */
    @PostMapping
    public Result<Long> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }
    
    /**
     * 更新分类
     * @param categoryDTO 分类DTO
     * @return 更新结果
     */
    @PutMapping
    public Result<Void> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryDTO);
    }
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param shopId 店铺ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(@PathVariable Long id, @RequestParam Long shopId) {
        return categoryService.deleteCategory(id, shopId);
    }
} 