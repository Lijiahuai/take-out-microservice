package com.takeout.user.mapper;

import com.takeout.user.entity.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车Mapper接口
 */
@Mapper
@Repository
public interface CartMapper {
    
    /**
     * 通过用户ID和店铺ID获取购物车
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 购物车
     */
    @Select("SELECT * FROM cart WHERE user_id = #{userId} AND shop_id = #{shopId}")
    Cart getCartByUserIdAndShopId(@Param("userId") Long userId, @Param("shopId") Long shopId);
    
    /**
     * 通过用户ID获取购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    List<Cart> getCartsByUserId(@Param("userId") Long userId);
    
    /**
     * 创建购物车
     * @param cart 购物车
     * @return 影响行数
     */
    @Insert("INSERT INTO cart(user_id, shop_id, shop_name, total_quantity, total_amount, create_time, update_time) " +
            "VALUES(#{userId}, #{shopId}, #{shopName}, #{totalQuantity}, #{totalAmount}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCart(Cart cart);
    
    /**
     * 更新购物车
     * @param cart 购物车
     * @return 影响行数
     */
    @Update("UPDATE cart SET total_quantity = #{totalQuantity}, total_amount = #{totalAmount}, update_time = NOW() " +
            "WHERE id = #{id}")
    int updateCart(Cart cart);
    
    /**
     * 删除购物车
     * @param id 购物车ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart WHERE id = #{id}")
    int deleteCart(@Param("id") Long id);
    
    /**
     * 清空用户购物车
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart WHERE user_id = #{userId}")
    int clearUserCarts(@Param("userId") Long userId);
} 