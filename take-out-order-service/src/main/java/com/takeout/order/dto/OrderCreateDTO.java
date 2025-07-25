package com.takeout.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单创建DTO
 */
@Data
public class OrderCreateDTO {
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 商家ID
     */
    private Long merchantId;
    
    /**
     * 店铺ID
     */
    private Long shopId;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 实付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 支付方式：1-余额支付，2-微信支付，3-支付宝支付
     */
    private Integer payType;
    
    /**
     * 配送地址
     */
    private String address;
    
    /**
     * 收货人
     */
    private String receiver;
    
    /**
     * 收货人电话
     */
    private String phone;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 订单项列表
     */
    private List<OrderItemDTO> orderItems;
} 