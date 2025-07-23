package com.takeout.dish.service.impl;

import com.takeout.common.constant.RedisKey;
import com.takeout.common.dto.Result;
import com.takeout.dish.dto.DishDTO;
import com.takeout.dish.entity.Dish;
import com.takeout.dish.mapper.DishMapper;
import com.takeout.dish.service.DishService;
import com.takeout.dish.vo.DishVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 菜品服务实现类
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public Result<DishVO> getDish(Long id) {
        // 先从缓存中获取
        String redisKey = RedisKey.DISH_PREFIX + id;
        Object cachedDish = redisTemplate.opsForValue().get(redisKey);
        
        if (cachedDish != null) {
            return Result.success((DishVO) cachedDish);
        }
        
        // 缓存中不存在，从数据库获取
        Dish dish = dishMapper.getDishById(id);
        if (dish == null) {
            return Result.error("菜品不存在");
        }
        
        // 转换为VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        
        // 存入缓存
        redisTemplate.opsForValue().set(redisKey, dishVO, 1, TimeUnit.HOURS);
        
        return Result.success(dishVO);
    }

    @Override
    public Result<List<DishVO>> getDishListByShopId(Long shopId) {
        // 先从缓存中获取
        String redisKey = RedisKey.DISH_LIST_PREFIX + "shop:" + shopId;
        Object cachedDishList = redisTemplate.opsForValue().get(redisKey);
        
        if (cachedDishList != null) {
            return Result.success((List<DishVO>) cachedDishList);
        }
        
        // 缓存中不存在，从数据库获取
        List<Dish> dishList = dishMapper.getDishListByShopId(shopId);
        
        // 转换为VO列表
        List<DishVO> dishVOList = dishList.stream()
                .map(dish -> {
                    DishVO dishVO = new DishVO();
                    BeanUtils.copyProperties(dish, dishVO);
                    return dishVO;
                })
                .collect(Collectors.toList());
        
        // 存入缓存
        redisTemplate.opsForValue().set(redisKey, dishVOList, 1, TimeUnit.HOURS);
        
        return Result.success(dishVOList);
    }

    @Override
    public Result<List<DishVO>> getDishListByCategoryId(Long categoryId) {
        // 先从缓存中获取
        String redisKey = RedisKey.DISH_LIST_PREFIX + "category:" + categoryId;
        Object cachedDishList = redisTemplate.opsForValue().get(redisKey);
        
        if (cachedDishList != null) {
            return Result.success((List<DishVO>) cachedDishList);
        }
        
        // 缓存中不存在，从数据库获取
        List<Dish> dishList = dishMapper.getDishListByCategoryId(categoryId);
        
        // 转换为VO列表
        List<DishVO> dishVOList = dishList.stream()
                .map(dish -> {
                    DishVO dishVO = new DishVO();
                    BeanUtils.copyProperties(dish, dishVO);
                    return dishVO;
                })
                .collect(Collectors.toList());
        
        // 存入缓存
        redisTemplate.opsForValue().set(redisKey, dishVOList, 1, TimeUnit.HOURS);
        
        return Result.success(dishVOList);
    }

    @Override
    public Result<Long> addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        
        int result = dishMapper.insertDish(dish);
        if (result <= 0) {
            return Result.error("添加菜品失败");
        }
        
        // 清除相关缓存
        clearCache(dish);
        
        return Result.success(dish.getId());
    }

    @Override
    public Result<Void> updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        
        int result = dishMapper.updateDish(dish);
        if (result <= 0) {
            return Result.error("更新菜品失败");
        }
        
        // 清除相关缓存
        clearCache(dish);
        
        return Result.success("更新菜品成功", null);
    }

    @Override
    public Result<Void> deleteDish(Long id) {
        // 先查询菜品信息，用于后续清除缓存
        Dish dish = dishMapper.getDishById(id);
        if (dish == null) {
            return Result.error("菜品不存在");
        }
        
        int result = dishMapper.deleteDish(id);
        if (result <= 0) {
            return Result.error("删除菜品失败");
        }
        
        // 清除相关缓存
        clearCache(dish);
        
        return Result.success("删除菜品成功", null);
    }

    @Override
    public Result<Void> updateDishSales(Long id, Integer count) {
        int result = dishMapper.updateDishSales(id, count);
        if (result <= 0) {
            return Result.error("更新菜品销量失败");
        }
        
        // 清除菜品缓存
        redisTemplate.delete(RedisKey.DISH_PREFIX + id);
        
        return Result.success("更新菜品销量成功", null);
    }
    
    /**
     * 清除菜品相关缓存
     * @param dish 菜品信息
     */
    private void clearCache(Dish dish) {
        // 清除菜品缓存
        redisTemplate.delete(RedisKey.DISH_PREFIX + dish.getId());
        
        // 清除店铺菜品列表缓存
        redisTemplate.delete(RedisKey.DISH_LIST_PREFIX + "shop:" + dish.getShopId());
        
        // 清除分类菜品列表缓存
        redisTemplate.delete(RedisKey.DISH_LIST_PREFIX + "category:" + dish.getCategoryId());
    }
} 