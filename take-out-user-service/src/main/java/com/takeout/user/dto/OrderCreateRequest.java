package com.takeout.user.dto;

import com.takeout.user.dto.SettlementRequest;
import com.takeout.user.entity.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequest {
    private SettlementRequest settlementRequest;
    private List<CartItem> cartItems;
}
