package com.takeout.merchant.service;

import com.takeout.common.dto.Result;
import com.takeout.merchant.dto.ShopDTO;
import com.takeout.merchant.vo.ShopVO;

import java.util.List;

/**
 * 店铺服务接口
 */
public interface ShopService {
    
    /**
     * 获取所有店铺
     * @return 店铺列表
     */
    Result<List<ShopVO>> getAllShops();
    
    /**
     * 获取店铺详情
     * @param id 店铺ID
     * @return 店铺详情
     */
    Result<ShopVO> getShopDetail(Long id);
    
    /**
     * 创建店铺
     * @param shopDTO 店铺DTO
     * @return 创建结果
     */
    Result<Long> createShop(ShopDTO shopDTO);
    
    /**
     * 更新店铺
     * @param shopDTO 店铺DTO
     * @return 更新结果
     */
    Result<Void> updateShop(ShopDTO shopDTO);
    
    /**
     * 更新店铺状态
     * @param id 店铺ID
     * @param status 状态
     * @return 更新结果
     */
    Result<Void> updateShopStatus(Long id, Integer status);
} 