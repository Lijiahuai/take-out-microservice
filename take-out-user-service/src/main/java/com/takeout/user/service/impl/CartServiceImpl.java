package com.takeout.user.service.impl;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.CartItemDTO;
import com.takeout.user.entity.Cart;
import com.takeout.user.entity.CartItem;
import com.takeout.user.mapper.CartItemMapper;
import com.takeout.user.mapper.CartMapper;
import com.takeout.user.service.CartService;
import com.takeout.user.vo.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    
    @Override
    public Result<List<CartVO>> getUserCarts(Long userId) {
        // 1. 获取用户所有购物车
        List<Cart> carts = cartMapper.getCartsByUserId(userId);
        
        // 2. 转换为VO列表
        List<CartVO> cartVOList = new ArrayList<>();
        for (Cart cart : carts) {
            // 获取购物车项
            List<CartItem> cartItems = cartItemMapper.getCartItemsByCartId(cart.getId());
            
            // 转换为VO
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(cart, cartVO);
            cartVO.setCartItems(cartItems);
            
            cartVOList.add(cartVO);
        }
        
        return Result.success(cartVOList);
    }

    @Override
    public Result<CartVO> getUserCartByShopId(Long userId, Long shopId) {
        // 1. 获取用户指定店铺的购物车
        Cart cart = cartMapper.getCartByUserIdAndShopId(userId, shopId);
        if (cart == null) {
            return Result.error("购物车不存在");
        }
        
        // 2. 获取购物车项
        List<CartItem> cartItems = cartItemMapper.getCartItemsByCartId(cart.getId());
        
        // 3. 转换为VO
        CartVO cartVO = new CartVO();
        BeanUtils.copyProperties(cart, cartVO);
        cartVO.setCartItems(cartItems);
        
        return Result.success(cartVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<CartItem> addToCart(Long userId, CartItemDTO cartItemDTO) {
        // 1. 查询用户是否已有该店铺的购物车
        Cart cart = cartMapper.getCartByUserIdAndShopId(userId, cartItemDTO.getShopId());
        
        // 2. 如果没有，创建购物车
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setShopId(cartItemDTO.getShopId());
            cart.setShopName(cartItemDTO.getShopName());
            cart.setTotalQuantity(0);
            cart.setTotalAmount(BigDecimal.ZERO);
            
            cartMapper.createCart(cart);
        }
        
        // 3. 查询购物车中是否已有该菜品
        List<CartItem> existingItems = cartItemMapper.getCartItemsByCartId(cart.getId());
        CartItem existingItem = existingItems.stream()
                .filter(item -> item.getDishId().equals(cartItemDTO.getDishId()))
                .findFirst()
                .orElse(null);
        
        // 4. 如果已有，更新数量和小计
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + cartItemDTO.getQuantity());
            existingItem.setSubtotal(existingItem.getPrice().multiply(new BigDecimal(existingItem.getQuantity())));
            
            cartItemMapper.updateCartItem(existingItem);
            
            // 更新购物车总数量和总金额
            updateCartTotals(cart.getId());
            
            return Result.success(existingItem);
        }
        
        // 5. 如果没有，创建购物车项
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartItemDTO, cartItem);
        cartItem.setCartId(cart.getId());
        cartItem.setUserId(userId);
        cartItem.setSubtotal(cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        
        cartItemMapper.createCartItem(cartItem);
        
        // 6. 更新购物车总数量和总金额
        updateCartTotals(cart.getId());
        
        return Result.success(cartItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<CartItem> updateCartItemQuantity(Long userId, Long cartItemId, Integer quantity) {
        // 1. 查询购物车项是否存在
        List<CartItem> cartItems = cartItemMapper.getCartItemsByIds(List.of(cartItemId), userId);
        if (cartItems.isEmpty()) {
            return Result.error("购物车项不存在");
        }
        
        CartItem cartItem = cartItems.get(0);
        
        // 2. 如果数量为0，删除购物车项
        if (quantity <= 0) {
            cartItemMapper.deleteCartItem(cartItemId);
            
            // 更新购物车总数量和总金额
            updateCartTotals(cartItem.getCartId());
            
            return Result.success(null);
        }
        
        // 3. 更新数量和小计
        cartItem.setQuantity(quantity);
        cartItem.setSubtotal(cartItem.getPrice().multiply(new BigDecimal(quantity)));
        
        cartItemMapper.updateCartItem(cartItem);
        
        // 4. 更新购物车总数量和总金额
        updateCartTotals(cartItem.getCartId());
        
        return Result.success(cartItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> removeCartItem(Long userId, Long cartItemId) {
        // 1. 查询购物车项是否存在
        List<CartItem> cartItems = cartItemMapper.getCartItemsByIds(List.of(cartItemId), userId);
        if (cartItems.isEmpty()) {
            return Result.error("购物车项不存在");
        }
        
        CartItem cartItem = cartItems.get(0);
        
        // 2. 删除购物车项
        cartItemMapper.deleteCartItem(cartItemId);
        
        // 3. 更新购物车总数量和总金额
        updateCartTotals(cartItem.getCartId());
        
        return Result.success("删除成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> clearUserCart(Long userId) {
        // 1. 获取用户所有购物车
        List<Cart> carts = cartMapper.getCartsByUserId(userId);
        
        // 2. 删除所有购物车项
        for (Cart cart : carts) {
            cartItemMapper.deleteCartItemsByCartId(cart.getId());
        }
        
        // 3. 删除所有购物车
        cartMapper.clearUserCarts(userId);
        
        return Result.success("清空成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> clearUserCartByShopId(Long userId, Long shopId) {
        // 1. 获取用户指定店铺的购物车
        Cart cart = cartMapper.getCartByUserIdAndShopId(userId, shopId);
        if (cart == null) {
            return Result.error("购物车不存在");
        }
        
        // 2. 删除购物车项
        cartItemMapper.deleteCartItemsByCartId(cart.getId());
        
        // 3. 删除购物车
        cartMapper.deleteCart(cart.getId());
        
        return Result.success("清空成功", null);
    }

    @Override
    public Result<List<CartItem>> getCartItemsByIds(Long userId, List<Long> cartItemIds) {
        if (cartItemIds == null || cartItemIds.isEmpty()) {
            return Result.error("购物车项ID列表不能为空");
        }
        
        List<CartItem> cartItems = cartItemMapper.getCartItemsByIds(cartItemIds, userId);
        
        return Result.success(cartItems);
    }
    
    /**
     * 更新购物车总数量和总金额
     * @param cartId 购物车ID
     */
    private void updateCartTotals(Long cartId) {
        List<CartItem> cartItems = cartItemMapper.getCartItemsByCartId(cartId);
        
        // 计算总数量和总金额
        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // 更新购物车
        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setTotalQuantity(totalQuantity);
        cart.setTotalAmount(totalAmount);
        
        cartMapper.updateCart(cart);
        
        // 如果购物车为空，删除购物车
        if (totalQuantity == 0) {
            cartMapper.deleteCart(cartId);
        }
    }
} 