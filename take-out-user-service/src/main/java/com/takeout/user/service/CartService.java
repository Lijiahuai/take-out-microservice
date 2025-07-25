package com.takeout.user.service;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.entity.Cart;
import com.takeout.user.entity.CartItem;
import com.takeout.user.vo.CartVO;

import java.util.List;

/**
 * 购物车服务接口
 */
public interface CartService {
    
    /**
     * 获取用户购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    Result<List<CartVO>> getUserCarts(Long userId);
    
    /**
     * 获取用户指定店铺的购物车
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 购物车
     */
    Result<CartVO> getUserCartByShopId(Long userId, Long shopId);
    
    /**
     * 添加商品到购物车
     * @param userId 用户ID
     * @param cartItemDTO 购物车项DTO
     * @return 添加结果
     */
    Result<CartItem> addToCart(Long userId, CartItemDTO cartItemDTO);
    
    /**
     * 更新购物车项数量
     * @param userId 用户ID
     * @param cartItemId 购物车项ID
     * @param quantity 数量
     * @return 更新结果
     */
    Result<CartItem> updateCartItemQuantity(Long userId, Long cartItemId, Integer quantity);
    
    /**
     * 删除购物车项
     * @param userId 用户ID
     * @param cartItemId 购物车项ID
     * @return 删除结果
     */
    Result<Void> removeCartItem(Long userId, Long cartItemId);
    
    /**
     * 清空用户购物车
     * @param userId 用户ID
     * @return 清空结果
     */
    Result<Void> clearUserCart(Long userId);
    
    /**
     * 清空用户指定店铺的购物车
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 清空结果
     */
    Result<Void> clearUserCartByShopId(Long userId, Long shopId);
    
    /**
     * 批量获取购物车项
     * @param userId 用户ID
     * @param cartItemIds 购物车项ID列表
     * @return 购物车项列表
     */
    Result<List<CartItem>> getCartItemsByIds(Long userId, List<Long> cartItemIds);
} 