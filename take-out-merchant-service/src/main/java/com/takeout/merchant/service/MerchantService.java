package com.takeout.merchant.service;

import com.takeout.common.dto.Result;
import com.takeout.merchant.entity.Merchant;

/**
 * 商家服务接口
 */
public interface MerchantService {
    
    /**
     * 获取商家信息
     * @param id 商家ID
     * @return 商家信息
     */
    Result<Merchant> getMerchant(Long id);
    
    /**
     * 通过店铺ID获取商家信息
     * @param shopId 店铺ID
     * @return 商家信息
     */
    Result<Merchant> getMerchantByShopId(Long shopId);
    
    /**
     * 更新商家信息
     * @param merchant 商家信息
     * @return 更新结果
     */
    Result<Void> updateMerchant(Merchant merchant);
    
    /**
     * 修改密码
     * @param id 商家ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    Result<Void> changePassword(Long id, String oldPassword, String newPassword);
} 