package com.takeout.user.dto;

import lombok.Data;

import java.util.List;

/**
 * 结算请求DTO
 */
@Data
public class SettlementRequest {
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 购物车项ID列表
     */
    private List<Long> cartItemIds;
    
    /**
     * 配送地址
     */
    private String address;
    
    /**
     * 联系人
     */
    private String contactName;
    
    /**
     * 联系电话
     */
    private String contactPhone;
    
    /**
     * 备注
     */
    private String remark;
} 