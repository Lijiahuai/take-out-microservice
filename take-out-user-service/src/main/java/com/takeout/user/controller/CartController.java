package com.takeout.user.controller;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.entity.CartItem;
import com.takeout.user.service.CartService;
import com.takeout.user.vo.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车控制器
 */
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;
    
    /**
     * 获取用户购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<CartVO>> getUserCarts(@PathVariable Long userId) {
        return cartService.getUserCarts(userId);
    }
    
    /**
     * 获取用户指定店铺的购物车
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 购物车
     */
    @GetMapping("/user/{userId}/shop/{shopId}")
    public Result<CartVO> getUserCartByShopId(@PathVariable Long userId, @PathVariable Long shopId) {
        return cartService.getUserCartByShopId(userId, shopId);
    }
    
    /**
     * 添加商品到购物车
     * @param userId 用户ID
     * @param cartItemDTO 购物车项DTO
     * @return 添加结果
     */
    @PostMapping("/user/{userId}")
    public Result<CartItem> addToCart(@PathVariable Long userId, @RequestBody CartItemDTO cartItemDTO) {
        return cartService.addToCart(userId, cartItemDTO);
    }
    
    /**
     * 更新购物车项数量
     * @param userId 用户ID
     * @param cartItemId 购物车项ID
     * @param quantity 数量
     * @return 更新结果
     */
    @PutMapping("/user/{userId}/item/{cartItemId}")
    public Result<CartItem> updateCartItemQuantity(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestParam Integer quantity) {
        return cartService.updateCartItemQuantity(userId, cartItemId, quantity);
    }
    
    /**
     * 删除购物车项
     * @param userId 用户ID
     * @param cartItemId 购物车项ID
     * @return 删除结果
     */
    @DeleteMapping("/user/{userId}/item/{cartItemId}")
    public Result<Void> removeCartItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        return cartService.removeCartItem(userId, cartItemId);
    }
    
    /**
     * 清空用户购物车
     * @param userId 用户ID
     * @return 清空结果
     */
    @DeleteMapping("/user/{userId}")
    public Result<Void> clearUserCart(@PathVariable Long userId) {
        return cartService.clearUserCart(userId);
    }
    
    /**
     * 清空用户指定店铺的购物车
     * @param userId 用户ID
     * @param shopId 店铺ID
     * @return 清空结果
     */
    @DeleteMapping("/user/{userId}/shop/{shopId}")
    public Result<Void> clearUserCartByShopId(@PathVariable Long userId, @PathVariable Long shopId) {
        return cartService.clearUserCartByShopId(userId, shopId);
    }
    
    /**
     * 批量获取购物车项
     * @param userId 用户ID
     * @param cartItemIds 购物车项ID列表
     * @return 购物车项列表
     */
    @PostMapping("/user/{userId}/items")
    public Result<List<CartItem>> getCartItemsByIds(@PathVariable Long userId, @RequestBody List<Long> cartItemIds) {
        return cartService.getCartItemsByIds(userId, cartItemIds);
    }
}