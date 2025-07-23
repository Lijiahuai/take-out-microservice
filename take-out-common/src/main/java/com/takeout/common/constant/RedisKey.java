package com.takeout.common.constant;

/**
 * Redis键常量
 */
public class RedisKey {
    // 用户认证相关
    public static final String USER_TOKEN_PREFIX = "user:token:";
    public static final String ADMIN_TOKEN_PREFIX = "admin:token:";
    
    // 购物车相关
    public static final String USER_CART_PREFIX = "user:cart:";
    
    // 订单相关
    public static final String ORDER_PREFIX = "order:";
    
    // 菜品相关
    public static final String DISH_PREFIX = "dish:";
    public static final String DISH_LIST_PREFIX = "dish:list:";
    
    // 店铺相关
    public static final String SHOP_PREFIX = "shop:";
    public static final String SHOP_LIST_PREFIX = "shop:list:";
} 