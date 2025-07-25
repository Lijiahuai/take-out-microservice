package com.takeout.user.vo;

import com.takeout.user.entity.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车VO
 */
@Data
public class CartVO {
    /**
     * 购物车ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 店铺名称
     */
    private String shopName;
    
    /**
     * 商品总数量
     */
    private Integer totalQuantity;
    
    /**
     * 商品总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 购物车项列表
     */
    private List<CartItem> cartItems;
} 