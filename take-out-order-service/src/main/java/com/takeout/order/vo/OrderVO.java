package com.takeout.order.vo;

import com.takeout.order.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单VO
 */
@Data
public class OrderVO {
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
     * 店铺名称
     */
    private String shopName;
    
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
     * 订单状态描述
     */
    private String statusDesc;
    
    /**
     * 支付方式：1-余额支付，2-微信支付，3-支付宝支付
     */
    private Integer payType;
    
    /**
     * 支付方式描述
     */
    private String payTypeDesc;
    
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
     * 订单项列表
     */
    private List<OrderItemVO> orderItems;
} 