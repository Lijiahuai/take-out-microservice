package com.takeout.user.controller;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.dto.SettlementRequest;
import com.takeout.user.entity.CartItem;
import com.takeout.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车控制器
 */
@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    
    /**
     * 获取购物车列表
     * @param userId 用户ID
     * @return 购物车项列表
     */
    @GetMapping
    public Result<List<CartItem>> getCartItems(@RequestHeader("X-User-Id") Long userId) {
        return cartService.getCartItems(userId);
    }
    
    /**
     * 添加到购物车
     * @param cartItemDTO 购物车项DTO
     * @param userId 用户ID
     * @return 添加结果
     */
    @PostMapping
    public Result<Void> addToCart(@RequestBody CartItemDTO cartItemDTO, @RequestHeader("X-User-Id") Long userId) {
        return cartService.addToCart(cartItemDTO, userId);
    }
    
    /**
     * 更新购物车项数量
     * @param id 购物车项ID
     * @param quantity 数量
     * @param userId 用户ID
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public Result<Void> updateCartItemQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity,
            @RequestHeader("X-User-Id") Long userId) {
        return cartService.updateCartItemQuantity(id, quantity, userId);
    }
    
    /**
     * 删除购物车项
     * @param id 购物车项ID
     * @param userId 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> removeFromCart(@PathVariable Long id, @RequestHeader("X-User-Id") Long userId) {
        return cartService.removeFromCart(id, userId);
    }
    
    /**
     * 清空购物车
     * @param userId 用户ID
     * @return 清空结果
     */
    @DeleteMapping
    public Result<Void> clearCart(@RequestHeader("X-User-Id") Long userId) {
        return cartService.clearCart(userId);
    }
    
    /**
     * 结算购物车
     * @param settlementRequest 结算请求
     * @param userId 用户ID
     * @return 结算结果
     */
    @PostMapping("/settlement")
    public Result<Long> settlement(@RequestBody SettlementRequest settlementRequest, @RequestHeader("X-User-Id") Long userId) {
        settlementRequest.setUserId(userId);
        return cartService.settlement(settlementRequest);
    }
} 