package com.takeout.user.service;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.dto.SettlementRequest;
import com.takeout.user.entity.CartItem;

import java.util.List;

/**
 * 购物车服务接口
 */
public interface CartService {
    
    /**
     * 获取购物车列表
     * @param userId 用户ID
     * @return 购物车项列表
     */
    Result<List<CartItem>> getCartItems(Long userId);
    
    /**
     * 添加到购物车
     * @param cartItemDTO 购物车项DTO
     * @param userId 用户ID
     * @return 添加结果
     */
    Result<Void> addToCart(CartItemDTO cartItemDTO, Long userId);
    
    /**
     * 更新购物车项数量
     * @param id 购物车项ID
     * @param quantity 数量
     * @param userId 用户ID
     * @return 更新结果
     */
    Result<Void> updateCartItemQuantity(Long id, Integer quantity, Long userId);
    
    /**
     * 删除购物车项
     * @param id 购物车项ID
     * @param userId 用户ID
     * @return 删除结果
     */
    Result<Void> removeFromCart(Long id, Long userId);
    
    /**
     * 清空购物车
     * @param userId 用户ID
     * @return 清空结果
     */
    Result<Void> clearCart(Long userId);
    
    /**
     * 结算购物车
     * @param settlementRequest 结算请求
     * @return 结算结果
     */
    Result<Long> settlement(SettlementRequest settlementRequest);
} 