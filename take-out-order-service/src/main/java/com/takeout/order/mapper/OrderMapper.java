package com.takeout.order.mapper;

import com.takeout.order.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Mapper接口
 */
@Mapper
@Repository
public interface OrderMapper {
    
    /**
     * 创建订单
     * @param order 订单信息
     * @return 影响行数
     */
    @Insert("INSERT INTO `order`(user_id, merchant_id, shop_id, order_no, total_amount, pay_amount, status, pay_type, address, receiver, phone, remark, create_time, update_time) " +
            "VALUES(#{userId}, #{merchantId}, #{shopId}, #{orderNo}, #{totalAmount}, #{payAmount}, #{status}, #{payType}, #{address}, #{receiver}, #{phone}, #{remark}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createOrder(Order order);
    
    /**
     * 根据ID查询订单
     * @param id 订单ID
     * @return 订单信息
     */
    @Select("SELECT * FROM `order` WHERE id = #{id}")
    Order getOrderById(@Param("id") Long id);
    
    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单信息
     */
    @Select("SELECT * FROM `order` WHERE order_no = #{orderNo}")
    Order getOrderByOrderNo(@Param("orderNo") String orderNo);
    
    /**
     * 根据用户ID查询订单列表
     * @param userId 用户ID
     * @return 订单列表
     */
    @Select("SELECT * FROM `order` WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Order> getOrderListByUserId(@Param("userId") Long userId);
    
    /**
     * 根据商家ID查询订单列表
     * @param merchantId 商家ID
     * @return 订单列表
     */
    @Select("SELECT * FROM `order` WHERE merchant_id = #{merchantId} ORDER BY create_time DESC")
    List<Order> getOrderListByMerchantId(@Param("merchantId") Long merchantId);
    
    /**
     * 根据店铺ID查询订单列表
     * @param shopId 店铺ID
     * @return 订单列表
     */
    @Select("SELECT * FROM `order` WHERE shop_id = #{shopId} ORDER BY create_time DESC")
    List<Order> getOrderListByShopId(@Param("shopId") Long shopId);
    
    /**
     * 更新订单状态
     * @param id 订单ID
     * @param status 订单状态
     * @return 影响行数
     */
    @Update("UPDATE `order` SET status = #{status}, update_time = NOW() WHERE id = #{id}")
    int updateOrderStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 更新订单支付信息
     * @param id 订单ID
     * @param payType 支付方式
     * @return 影响行数
     */
    @Update("UPDATE `order` SET status = 1, pay_type = #{payType}, pay_time = NOW(), update_time = NOW() WHERE id = #{id}")
    int updateOrderPayInfo(@Param("id") Long id, @Param("payType") Integer payType);
} 