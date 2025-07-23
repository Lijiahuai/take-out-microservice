package com.takeout.dish.service;

import com.takeout.common.dto.Result;
import com.takeout.dish.dto.DishDTO;
import com.takeout.dish.entity.Dish;
import com.takeout.dish.vo.DishVO;

import java.util.List;

/**
 * 菜品服务接口
 */
public interface DishService {
    
    /**
     * 根据ID获取菜品
     * @param id 菜品ID
     * @return 菜品
     */
    Result<DishVO> getDish(Long id);
    
    /**
     * 根据店铺ID获取菜品列表
     * @param shopId 店铺ID
     * @return 菜品列表
     */
    Result<List<DishVO>> getDishListByShopId(Long shopId);
    
    /**
     * 根据分类ID获取菜品列表
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    Result<List<DishVO>> getDishListByCategoryId(Long categoryId);
    
    /**
     * 添加菜品
     * @param dishDTO 菜品DTO
     * @return 添加结果
     */
    Result<Long> addDish(DishDTO dishDTO);
    
    /**
     * 更新菜品
     * @param dishDTO 菜品DTO
     * @return 更新结果
     */
    Result<Void> updateDish(DishDTO dishDTO);
    
    /**
     * 删除菜品
     * @param id 菜品ID
     * @return 删除结果
     */
    Result<Void> deleteDish(Long id);
    
    /**
     * 更新菜品销量
     * @param id 菜品ID
     * @param count 销量增加数
     * @return 更新结果
     */
    Result<Void> updateDishSales(Long id, Integer count);
} 