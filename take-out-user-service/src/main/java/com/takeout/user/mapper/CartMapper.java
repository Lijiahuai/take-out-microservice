package com.takeout.user.mapper;

import com.takeout.user.entity.CartItem;
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
     * 通过用户ID查询购物车项
     * @param userId 用户ID
     * @return 购物车项列表
     */
    @Select("SELECT * FROM cart_item WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<CartItem> getCartItemsByUserId(@Param("userId") Long userId);
    
    /**
     * 通过用户ID和菜品ID查询购物车项
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
    @Insert("INSERT INTO cart_item(user_id, dish_id, dish_name, dish_image, price, quantity, shop_id, shop_name, create_time, update_time) " +
            "VALUES(#{userId}, #{dishId}, #{dishName}, #{dishImage}, #{price}, #{quantity}, #{shopId}, #{shopName}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCartItem(CartItem cartItem);
    
    /**
     * 更新购物车项数量
     * @param id 购物车项ID
     * @param quantity 数量
     * @return 影响行数
     */
    @Update("UPDATE cart_item SET quantity = #{quantity}, update_time = NOW() WHERE id = #{id}")
    int updateCartItemQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);
    
    /**
     * 删除购物车项
     * @param id 购物车项ID
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart_item WHERE id = #{id} AND user_id = #{userId}")
    int deleteCartItem(@Param("id") Long id, @Param("userId") Long userId);
    
    /**
     * 清空购物车
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM cart_item WHERE user_id = #{userId}")
    int clearCart(@Param("userId") Long userId);
    
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