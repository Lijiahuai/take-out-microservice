package com.takeout.merchant.controller;

import com.takeout.common.dto.Result;
import com.takeout.merchant.entity.Merchant;
import com.takeout.merchant.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 商家控制器
 */
@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    
    /**
     * 获取商家信息
     * @param id 商家ID
     * @return 商家信息
     */
    @GetMapping("/{id}")
    public Result<Merchant> getMerchant(@PathVariable Long id) {
        return merchantService.getMerchant(id);
    }
    
    /**
     * 通过店铺ID获取商家信息
     * @param shopId 店铺ID
     * @return 商家信息
     */
    @GetMapping("/shop/{shopId}")
    public Result<Merchant> getMerchantByShopId(@PathVariable Long shopId) {
        return merchantService.getMerchantByShopId(shopId);
    }
    
    /**
     * 更新商家信息
     * @param merchant 商家信息
     * @return 更新结果
     */
    @PutMapping
    public Result<Void> updateMerchant(@RequestBody Merchant merchant) {
        return merchantService.updateMerchant(merchant);
    }
    
    /**
     * 修改密码
     * @param id 商家ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    @PostMapping("/{id}/password")
    public Result<Void> changePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        return merchantService.changePassword(id, oldPassword, newPassword);
    }
} 