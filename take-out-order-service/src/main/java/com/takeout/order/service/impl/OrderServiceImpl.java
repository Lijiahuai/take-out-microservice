package com.takeout.order.service.impl;

import com.takeout.common.dto.Result;
import com.takeout.order.dto.OrderCreateDTO;
import com.takeout.order.dto.OrderItemDTO;
import com.takeout.order.entity.Order;
import com.takeout.order.entity.OrderItem;
import com.takeout.order.mapper.OrderItemMapper;
import com.takeout.order.mapper.OrderMapper;
import com.takeout.order.service.OrderService;
import com.takeout.order.vo.OrderItemVO;
import com.takeout.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Long> createOrder(OrderCreateDTO orderCreateDTO) {
        // 1. 创建订单
        Order order = new Order();
        BeanUtils.copyProperties(orderCreateDTO, order);
        
        // 生成订单编号：时间戳 + 随机数
        String orderNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + UUID.randomUUID().toString().substring(0, 6);
        order.setOrderNo(orderNo);
        
        // 设置订单状态为待支付
        order.setStatus(0);
        
        // 保存订单
        int result = orderMapper.createOrder(order);
        if (result <= 0) {
            return Result.error("创建订单失败");
        }
        
        // 2. 保存订单项
        List<OrderItem> orderItems = orderCreateDTO.getOrderItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(item, orderItem);
                    orderItem.setOrderId(order.getId());
                    return orderItem;
                })
                .collect(Collectors.toList());
        
        result = orderItemMapper.batchCreateOrderItems(orderItems);
        if (result <= 0) {
            throw new RuntimeException("创建订单项失败");
        }
        
        return Result.success(order.getId());
    }

    @Override
    public Result<OrderVO> getOrderById(Long id) {
        // 1. 查询订单
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 查询订单项
        List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderId(id);
        
        // 3. 转换为VO
        OrderVO orderVO = convertToOrderVO(order, orderItems);
        
        return Result.success(orderVO);
    }

    @Override
    public Result<OrderVO> getOrderByOrderNo(String orderNo) {
        // 1. 查询订单
        Order order = orderMapper.getOrderByOrderNo(orderNo);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 查询订单项
        List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderId(order.getId());
        
        // 3. 转换为VO
        OrderVO orderVO = convertToOrderVO(order, orderItems);
        
        return Result.success(orderVO);
    }

    @Override
    public Result<List<OrderVO>> getUserOrderList(Long userId) {
        // 1. 查询订单列表
        List<Order> orders = orderMapper.getOrderListByUserId(userId);
        
        // 2. 转换为VO列表
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderId(order.getId());
            OrderVO orderVO = convertToOrderVO(order, orderItems);
            orderVOList.add(orderVO);
        }
        
        return Result.success(orderVOList);
    }

    @Override
    public Result<List<OrderVO>> getMerchantOrderList(Long merchantId) {
        // 1. 查询订单列表
        List<Order> orders = orderMapper.getOrderListByMerchantId(merchantId);
        
        // 2. 转换为VO列表
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderId(order.getId());
            OrderVO orderVO = convertToOrderVO(order, orderItems);
            orderVOList.add(orderVO);
        }
        
        return Result.success(orderVOList);
    }

    @Override
    public Result<List<OrderVO>> getShopOrderList(Long shopId) {
        // 1. 查询订单列表
        List<Order> orders = orderMapper.getOrderListByShopId(shopId);
        
        // 2. 转换为VO列表
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemMapper.getOrderItemsByOrderId(order.getId());
            OrderVO orderVO = convertToOrderVO(order, orderItems);
            orderVOList.add(orderVO);
        }
        
        return Result.success(orderVOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> payOrder(Long id, Integer payType) {
        // 1. 查询订单
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 检查订单状态
        if (order.getStatus() != 0) {
            return Result.error("订单状态不正确，无法支付");
        }
        
        // 3. 更新订单支付信息
        int result = orderMapper.updateOrderPayInfo(id, payType);
        if (result <= 0) {
            return Result.error("支付失败");
        }
        
        return Result.success("支付成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> cancelOrder(Long id) {
        // 1. 查询订单
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 检查订单状态
        if (order.getStatus() != 0) {
            return Result.error("订单状态不正确，无法取消");
        }
        
        // 3. 更新订单状态为已取消
        int result = orderMapper.updateOrderStatus(id, 4);
        if (result <= 0) {
            return Result.error("取消失败");
        }
        
        return Result.success("取消成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> completeOrder(Long id) {
        // 1. 查询订单
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 检查订单状态
        if (order.getStatus() != 2) {
            return Result.error("订单状态不正确，无法完成");
        }
        
        // 3. 更新订单状态为已完成
        int result = orderMapper.updateOrderStatus(id, 3);
        if (result <= 0) {
            return Result.error("完成失败");
        }
        
        return Result.success("完成成功", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> deliverOrder(Long id) {
        // 1. 查询订单
        Order order = orderMapper.getOrderById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        // 2. 检查订单状态
        if (order.getStatus() != 1) {
            return Result.error("订单状态不正确，无法配送");
        }
        
        // 3. 更新订单状态为配送中
        int result = orderMapper.updateOrderStatus(id, 2);
        if (result <= 0) {
            return Result.error("配送失败");
        }
        
        return Result.success("配送成功", null);
    }
    
    /**
     * 将订单和订单项转换为OrderVO
     * @param order 订单
     * @param orderItems 订单项列表
     * @return OrderVO
     */
    private OrderVO convertToOrderVO(Order order, List<OrderItem> orderItems) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        
        // 设置订单状态描述
        switch (order.getStatus()) {
            case 0:
                orderVO.setStatusDesc("待支付");
                break;
            case 1:
                orderVO.setStatusDesc("已支付");
                break;
            case 2:
                orderVO.setStatusDesc("配送中");
                break;
            case 3:
                orderVO.setStatusDesc("已完成");
                break;
            case 4:
                orderVO.setStatusDesc("已取消");
                break;
            default:
                orderVO.setStatusDesc("未知状态");
        }
        
        // 设置支付方式描述
        if (order.getPayType() != null) {
            switch (order.getPayType()) {
                case 1:
                    orderVO.setPayTypeDesc("余额支付");
                    break;
                case 2:
                    orderVO.setPayTypeDesc("微信支付");
                    break;
                case 3:
                    orderVO.setPayTypeDesc("支付宝支付");
                    break;
                default:
                    orderVO.setPayTypeDesc("未知支付方式");
            }
        }
        
        // 转换订单项
        List<OrderItemVO> orderItemVOList = orderItems.stream()
                .map(item -> {
                    OrderItemVO orderItemVO = new OrderItemVO();
                    BeanUtils.copyProperties(item, orderItemVO);
                    return orderItemVO;
                })
                .collect(Collectors.toList());
        
        orderVO.setOrderItems(orderItemVOList);
        
        return orderVO;
    }
} 