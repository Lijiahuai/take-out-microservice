package com.takeout.order.service;

import com.takeout.common.dto.Result;
import com.takeout.order.dto.OrderCreateDTO;
import com.takeout.order.entity.Order;
import com.takeout.order.vo.OrderVO;

import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService {
    
    /**
     * 创建订单
     * @param orderCreateDTO 订单创建DTO
     * @return 订单ID
     */
    Result<Long> createOrder(OrderCreateDTO orderCreateDTO);
    
    /**
     * 根据ID获取订单详情
     * @param id 订单ID
     * @return 订单详情
     */
    Result<OrderVO> getOrderById(Long id);
    
    /**
     * 根据订单编号获取订单详情
     * @param orderNo 订单编号
     * @return 订单详情
     */
    Result<OrderVO> getOrderByOrderNo(String orderNo);
    
    /**
     * 获取用户订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    Result<List<OrderVO>> getUserOrderList(Long userId);
    
    /**
     * 获取商家订单列表
     * @param merchantId 商家ID
     * @return 订单列表
     */
    Result<List<OrderVO>> getMerchantOrderList(Long merchantId);
    
    /**
     * 获取店铺订单列表
     * @param shopId 店铺ID
     * @return 订单列表
     */
    Result<List<OrderVO>> getShopOrderList(Long shopId);
    
    /**
     * 支付订单
     * @param id 订单ID
     * @param payType 支付方式
     * @return 支付结果
     */
    Result<Void> payOrder(Long id, Integer payType);
    
    /**
     * 取消订单
     * @param id 订单ID
     * @return 取消结果
     */
    Result<Void> cancelOrder(Long id);
    
    /**
     * 完成订单
     * @param id 订单ID
     * @return 完成结果
     */
    Result<Void> completeOrder(Long id);
    
    /**
     * 配送订单
     * @param id 订单ID
     * @return 配送结果
     */
    Result<Void> deliverOrder(Long id);
} 