package com.takeout.dish.mapper;

import com.takeout.dish.entity.Dish;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜品Mapper接口
 */
@Mapper
@Repository
public interface DishMapper {
    
    /**
     * 根据ID查询菜品
     * @param id 菜品ID
     * @return 菜品
     */
    @Select("SELECT d.*, c.name as category_name, s.name as shop_name " +
            "FROM dish d " +
            "LEFT JOIN category c ON d.category_id = c.id " +
            "LEFT JOIN shop s ON d.shop_id = s.id " +
            "WHERE d.id = #{id}")
    Dish getDishById(@Param("id") Long id);
    
    /**
     * 根据店铺ID查询菜品列表
     * @param shopId 店铺ID
     * @return 菜品列表
     */
    @Select("SELECT d.*, c.name as category_name, s.name as shop_name " +
            "FROM dish d " +
            "LEFT JOIN category c ON d.category_id = c.id " +
            "LEFT JOIN shop s ON d.shop_id = s.id " +
            "WHERE d.shop_id = #{shopId} " +
            "ORDER BY d.category_id, d.sort, d.create_time DESC")
    List<Dish> getDishListByShopId(@Param("shopId") Long shopId);
    
    /**
     * 根据分类ID查询菜品列表
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    @Select("SELECT d.*, c.name as category_name, s.name as shop_name " +
            "FROM dish d " +
            "LEFT JOIN category c ON d.category_id = c.id " +
            "LEFT JOIN shop s ON d.shop_id = s.id " +
            "WHERE d.category_id = #{categoryId} " +
            "ORDER BY d.sort, d.create_time DESC")
    List<Dish> getDishListByCategoryId(@Param("categoryId") Long categoryId);
    
    /**
     * 添加菜品
     * @param dish 菜品
     * @return 影响行数
     */
    @Insert("INSERT INTO dish(name, description, image, price, status, category_id, shop_id, sales, create_time, update_time) " +
            "VALUES(#{name}, #{description}, #{image}, #{price}, #{status}, #{categoryId}, #{shopId}, 0, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertDish(Dish dish);
    
    /**
     * 更新菜品
     * @param dish 菜品
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE dish SET update_time = NOW() " +
            "<if test='name != null'>, name = #{name}</if>" +
            "<if test='description != null'>, description = #{description}</if>" +
            "<if test='image != null'>, image = #{image}</if>" +
            "<if test='price != null'>, price = #{price}</if>" +
            "<if test='status != null'>, status = #{status}</if>" +
            "<if test='categoryId != null'>, category_id = #{categoryId}</if>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateDish(Dish dish);
    
    /**
     * 删除菜品
     * @param id 菜品ID
     * @return 影响行数
     */
    @Delete("DELETE FROM dish WHERE id = #{id}")
    int deleteDish(@Param("id") Long id);
    
    /**
     * 更新菜品销量
     * @param id 菜品ID
     * @param count 销量增加数
     * @return 影响行数
     */
    @Update("UPDATE dish SET sales = sales + #{count}, update_time = NOW() WHERE id = #{id}")
    int updateDishSales(@Param("id") Long id, @Param("count") Integer count);
} 