package com.takeout.user.service.impl;

import com.takeout.common.constant.RedisKey;
import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.dto.SettlementRequest;
import com.takeout.user.entity.CartItem;
import com.takeout.user.feign.OrderFeignClient;
import com.takeout.user.mapper.CartMapper;
import com.takeout.user.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 购物车服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderFeignClient orderFeignClient;
    
    @Override
    public Result<List<CartItem>> getCartItems(Long userId) {
        List<CartItem> cartItems = cartMapper.getCartItemsByUserId(userId);
        return Result.success(cartItems);
    }

    @Override
    public Result<Void> addToCart(CartItemDTO cartItemDTO, Long userId) {
        // 先查询是否已存在相同菜品
        CartItem existItem = cartMapper.getCartItemByUserIdAndDishId(userId, cartItemDTO.getDishId());
        
        if (existItem != null) {
            // 如果已存在，则更新数量
            int newQuantity = existItem.getQuantity() + cartItemDTO.getQuantity();
            int result = cartMapper.updateCartItemQuantity(existItem.getId(), newQuantity);
            if (result > 0) {
                return Result.success("更新购物车成功", null);
            } else {
                return Result.error("更新购物车失败");
            }
        } else {
            // 如果不存在，则添加新的购物车项
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setDishId(cartItemDTO.getDishId());
            cartItem.setDishName(cartItemDTO.getDishName());
            cartItem.setDishImage(cartItemDTO.getDishImage());
            cartItem.setPrice(cartItemDTO.getPrice());
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setShopId(cartItemDTO.getShopId());
            cartItem.setShopName(cartItemDTO.getShopName());
            
            int result = cartMapper.createCartItem(cartItem);
            if (result > 0) {
                return Result.success("添加到购物车成功", null);
            } else {
                return Result.error("添加到购物车失败");
            }
        }
    }

    @Override
    public Result<Void> updateCartItemQuantity(Long id, Integer quantity, Long userId) {
        if (quantity <= 0) {
            return removeFromCart(id, userId);
        }
        
        int result = cartMapper.updateCartItemQuantity(id, quantity);
        if (result > 0) {
            return Result.success("更新数量成功", null);
        } else {
            return Result.error("更新数量失败");
        }
    }

    @Override
    public Result<Void> removeFromCart(Long id, Long userId) {
        int result = cartMapper.deleteCartItem(id, userId);
        if (result > 0) {
            return Result.success("删除成功", null);
        } else {
            return Result.error("删除失败");
        }
    }

    @Override
    public Result<Void> clearCart(Long userId) {
        int result = cartMapper.clearCart(userId);
        if (result >= 0) {
            return Result.success("清空购物车成功", null);
        } else {
            return Result.error("清空购物车失败");
        }
    }

    @Override
    @Transactional
    public Result<Long> settlement(SettlementRequest settlementRequest) {
        // 1. 获取要结算的购物车项
        List<CartItem> cartItems = cartMapper.getCartItemsByIds(settlementRequest.getCartItemIds(), settlementRequest.getUserId());
        if (cartItems == null || cartItems.isEmpty()) {
            return Result.error("购物车为空");
        }
        
        // 2. 调用订单服务创建订单
        Result<Long> orderResult = orderFeignClient.createOrder(settlementRequest, cartItems);
        if (orderResult == null || orderResult.getCode() != 200 || orderResult.getData() == null) {
            log.error("创建订单失败: {}", orderResult);
            return Result.error("创建订单失败");
        }
        
        // 3. 订单创建成功后，删除购物车中的相关项目
        for (CartItem cartItem : cartItems) {
            cartMapper.deleteCartItem(cartItem.getId(), settlementRequest.getUserId());
        }
        
        // 4. 将订单ID存入Redis，用于前端轮询查询订单状态
        String redisKey = RedisKey.ORDER_PREFIX + orderResult.getData();
        redisTemplate.opsForValue().set(redisKey, "CREATED", 1, TimeUnit.HOURS);
        
        return orderResult;
    }
} 