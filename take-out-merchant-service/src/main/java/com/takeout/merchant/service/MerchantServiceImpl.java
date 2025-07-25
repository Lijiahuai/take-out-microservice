package com.takeout.merchant.service;

import com.takeout.common.dto.Result;
import com.takeout.merchant.entity.Merchant;

public class MerchantServiceImpl implements MerchantService {
    @Override
    public Result<Merchant> getMerchant(Long id) {
        return null;
    }

    @Override
    public Result<Merchant> getMerchantByShopId(Long shopId) {
        return null;
    }

    @Override
    public Result<Void> updateMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public Result<Void> changePassword(Long id, String oldPassword, String newPassword) {
        return null;
    }
}
