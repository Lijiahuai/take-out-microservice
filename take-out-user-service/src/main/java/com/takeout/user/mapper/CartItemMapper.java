package com.takeout.user.mapper;

import com.takeout.user.entity.CartItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车项Mapper接口
 */
@Mapper
@Repository
public interface CartItemMapper {
    
    /**
     * 通过购物车ID获取购物车项列表
     * @param cartId 购物车ID
     * @return 购物车项列表
     */
    @Select("SELECT * FROM cart_item WHERE cart_id = #{cartId}")
    List<CartItem> getCartItemsByCartId(@Param("cartId") Long cartId);
    
    /**
     * 通过用户ID获取购物车项列表
     * @param userId 用户ID
     * @return 购物车项列表
     */
    @Select("SELECT * FROM cart_item WHERE user_id = #{userId}")
    List<CartItem> getCartItemsByUserId(@Param("userId") Long userId);
    
    /**
     * 通过用户ID和店铺ID获取购物车项列表
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 购物车项列表
     */
    @Select("SELECT * FROM cart_item WHERE user_id = #{userId} AND shop_id = #{shopId}")
    List<CartItem> getCartItemsByUserIdAndShopId(@Param("userId") Long userId, @Param("shopId") Long shopId);
    
    /**
     * 通过用户ID和菜品ID获取购物车项
     * @param userId 用户ID
     * @param dishId 菜品ID
     * @return 购物车项
     */
    @Select("SELECT * FROM cart_item WHERE user_id = #{userId} AND dish_id = #{dishId} LIMIT 1")
    CartItem getCartItemByUserIdAndDishId(@Param("userId") Long userId, @Param("dishId") Long dishId);
    
    /**
     * 创建购物车项
     * @param cartItem 购物车项
     * @return 影响行数
     */
    @Insert("INSERT INTO cart_item(cart_id, user_id, shop_id, dish_id, dish_name, dish_image, price, quantity, subtotal, create_time, update_time) " +
            "VALUES(#{cartId}, #{userId}, #{shopId}, #{dishId}, #{dishName}, #{dishImage}, #{price}, #{quantity}, #{subtotal}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCartItem(CartItem cartItem);
    
    /**
     * 更新购物车项
     * @param cartItem 购物车项
     * @return 影响行数
     */
    @Update("UPDATE cart_item SET quantity = #{quantity}, subtotal = #{subtotal}, update_time = NOW() " +
            "WHERE id = #{id}")
    int updateCartItem(CartItem cartItem);
    
    /**
     * 删除购物车项
     * @param id 购物车项ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart_item WHERE id = #{id}")
    int deleteCartItem(@Param("id") Long id);
    
    /**
     * 删除购物车所有项
     * @param cartId 购物车ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart_item WHERE cart_id = #{cartId}")
    int deleteCartItemsByCartId(@Param("cartId") Long cartId);
    
    /**
     * 批量获取购物车项
     * @param ids 购物车项ID列表
     * @param userId 用户ID
     * @return 购物车项列表
     */
    @Select("<script>" +
            "SELECT * FROM cart_item WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            " AND user_id = #{userId}" +
            "</script>")
    List<CartItem> getCartItemsByIds(@Param("ids") List<Long> ids, @Param("userId") Long userId);
} 