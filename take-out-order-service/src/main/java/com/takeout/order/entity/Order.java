package com.takeout.order.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单实体
 */
@Data
public class Order {
    /**
     * 订单ID
     */
    private Long id;
    
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
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 实付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 订单状态：0-待支付，1-已支付，2-配送中，3-已完成，4-已取消
     */
    private Integer status;
    
    /**
     * 支付方式：1-余额支付，2-微信支付，3-支付宝支付
     */
    private Integer payType;
    
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    
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
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 订单项列表（非数据库字段）
     */
    private transient List<OrderItem> orderItems;
} 