package com.takeout.order.controller;

import com.takeout.common.dto.Result;
import com.takeout.order.dto.OrderCreateDTO;
import com.takeout.order.service.OrderService;
import com.takeout.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    
    /**
     * 创建订单
     * @param orderCreateDTO 订单创建DTO
     * @return 订单ID
     */
    @PostMapping
    public Result<Long> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        return orderService.createOrder(orderCreateDTO);
    }
    
    /**
     * 根据ID获取订单详情
     * @param id 订单ID
     * @return 订单详情
     */
    @GetMapping("/{id}")
    public Result<OrderVO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    
    /**
     * 根据订单编号获取订单详情
     * @param orderNo 订单编号
     * @return 订单详情
     */
    @GetMapping("/no/{orderNo}")
    public Result<OrderVO> getOrderByOrderNo(@PathVariable String orderNo) {
        return orderService.getOrderByOrderNo(orderNo);
    }
    
    /**
     * 获取用户订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<OrderVO>> getUserOrderList(@PathVariable Long userId) {
        return orderService.getUserOrderList(userId);
    }
    
    /**
     * 获取商家订单列表
     * @param merchantId 商家ID
     * @return 订单列表
     */
    @GetMapping("/merchant/{merchantId}")
    public Result<List<OrderVO>> getMerchantOrderList(@PathVariable Long merchantId) {
        return orderService.getMerchantOrderList(merchantId);
    }
    
    /**
     * 获取店铺订单列表
     * @param shopId 店铺ID
     * @return 订单列表
     */
    @GetMapping("/shop/{shopId}")
    public Result<List<OrderVO>> getShopOrderList(@PathVariable Long shopId) {
        return orderService.getShopOrderList(shopId);
    }
    
    /**
     * 支付订单
     * @param id 订单ID
     * @param payType 支付方式
     * @return 支付结果
     */
    @PutMapping("/{id}/pay")
    public Result<Void> payOrder(@PathVariable Long id, @RequestParam Integer payType) {
        return orderService.payOrder(id, payType);
    }
    
    /**
     * 取消订单
     * @param id 订单ID
     * @return 取消结果
     */
    @PutMapping("/{id}/cancel")
    public Result<Void> cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }
    
    /**
     * 完成订单
     * @param id 订单ID
     * @return 完成结果
     */
    @PutMapping("/{id}/complete")
    public Result<Void> completeOrder(@PathVariable Long id) {
        return orderService.completeOrder(id);
    }
    
    /**
     * 配送订单
     * @param id 订单ID
     * @return 配送结果
     */
    @PutMapping("/{id}/deliver")
    public Result<Void> deliverOrder(@PathVariable Long id) {
        return orderService.deliverOrder(id);
    }
} 