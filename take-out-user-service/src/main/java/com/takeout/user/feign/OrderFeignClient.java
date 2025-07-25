package com.takeout.user.feign;

import com.takeout.common.dto.Result;
import com.takeout.user.dto.OrderCreateRequest;
import com.takeout.user.dto.SettlementRequest;
import com.takeout.user.entity.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 订单服务Feign客户端
 */
@FeignClient(value = "take-out-order-service", path = "/order")
public interface OrderFeignClient {
    /**
     * 创建订单
     * @return 订单ID
     */
    @PostMapping("/create")
    Result<Long> createOrder(@RequestBody OrderCreateRequest request);
}
