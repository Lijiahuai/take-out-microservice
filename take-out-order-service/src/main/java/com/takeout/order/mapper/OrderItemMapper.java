package com.takeout.order.mapper;

import com.takeout.order.entity.OrderItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单项Mapper接口
 */
@Mapper
@Repository
public interface OrderItemMapper {
    
    /**
     * 批量创建订单项
     * @param orderItems 订单项列表
     * @return 影响行数
     */
    @Insert("<script>" +
            "INSERT INTO order_item(order_id, dish_id, dish_name, dish_image, price, quantity, subtotal, create_time, update_time) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.orderId}, #{item.dishId}, #{item.dishName}, #{item.dishImage}, #{item.price}, #{item.quantity}, #{item.subtotal}, NOW(), NOW())" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int batchCreateOrderItems(@Param("list") List<OrderItem> orderItems);
    
    /**
     * 根据订单ID查询订单项列表
     * @param orderId 订单ID
     * @return 订单项列表
     */
    @Select("SELECT * FROM order_item WHERE order_id = #{orderId}")
    List<OrderItem> getOrderItemsByOrderId(@Param("orderId") Long orderId);
} 