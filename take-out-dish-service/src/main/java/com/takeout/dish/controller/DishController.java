package com.takeout.dish.controller;

import com.takeout.common.dto.Result;
import com.takeout.dish.dto.DishDTO;
import com.takeout.dish.service.DishService;
import com.takeout.dish.vo.DishVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品控制器
 */
@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    
    /**
     * 根据ID获取菜品
     * @param id 菜品ID
     * @return 菜品
     */
    @GetMapping("/{id}")
    public Result<DishVO> getDish(@PathVariable Long id) {
        return dishService.getDish(id);
    }
    
    /**
     * 根据店铺ID获取菜品列表
     * @param shopId 店铺ID
     * @return 菜品列表
     */
    @GetMapping("/shop/{shopId}")
    public Result<List<DishVO>> getDishListByShopId(@PathVariable Long shopId) {
        return dishService.getDishListByShopId(shopId);
    }
    
    /**
     * 根据分类ID获取菜品列表
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    @GetMapping("/category/{categoryId}")
    public Result<List<DishVO>> getDishListByCategoryId(@PathVariable Long categoryId) {
        return dishService.getDishListByCategoryId(categoryId);
    }
    
    /**
     * 添加菜品
     * @param dishDTO 菜品DTO
     * @return 添加结果
     */
    @PostMapping
    public Result<Long> addDish(@RequestBody DishDTO dishDTO) {
        return dishService.addDish(dishDTO);
    }
    
    /**
     * 更新菜品
     * @param dishDTO 菜品DTO
     * @return 更新结果
     */
    @PutMapping
    public Result<Void> updateDish(@RequestBody DishDTO dishDTO) {
        return dishService.updateDish(dishDTO);
    }
    
    /**
     * 删除菜品
     * @param id 菜品ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteDish(@PathVariable Long id) {
        return dishService.deleteDish(id);
    }
    
    /**
     * 更新菜品销量
     * @param id 菜品ID
     * @param count 销量增加数
     * @return 更新结果
     */
    @PutMapping("/{id}/sales")
    public Result<Void> updateDishSales(@PathVariable Long id, @RequestParam Integer count) {
        return dishService.updateDishSales(id, count);
    }
} 