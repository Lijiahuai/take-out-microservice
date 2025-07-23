package com.takeout.merchant.controller;

import com.takeout.common.dto.Result;
import com.takeout.merchant.dto.ShopDTO;
import com.takeout.merchant.service.ShopService;
import com.takeout.merchant.vo.ShopVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺控制器
 */
@RestController
@RequestMapping("/merchant/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    
    /**
     * 获取所有店铺
     * @return 店铺列表
     */
    @GetMapping
    public Result<List<ShopVO>> getAllShops() {
        return shopService.getAllShops();
    }
    
    /**
     * 获取店铺详情
     * @param id 店铺ID
     * @return 店铺详情
     */
    @GetMapping("/{id}")
    public Result<ShopVO> getShopDetail(@PathVariable Long id) {
        return shopService.getShopDetail(id);
    }
    
    /**
     * 创建店铺
     * @param shopDTO 店铺DTO
     * @return 创建结果
     */
    @PostMapping
    public Result<Long> createShop(@RequestBody ShopDTO shopDTO) {
        return shopService.createShop(shopDTO);
    }
    
    /**
     * 更新店铺
     * @param shopDTO 店铺DTO
     * @return 更新结果
     */
    @PutMapping
    public Result<Void> updateShop(@RequestBody ShopDTO shopDTO) {
        return shopService.updateShop(shopDTO);
    }
    
    /**
     * 更新店铺状态
     * @param id 店铺ID
     * @param status 状态
     * @return 更新结果
     */
    @PutMapping("/{id}/status")
    public Result<Void> updateShopStatus(@PathVariable Long id, @RequestParam Integer status) {
        return shopService.updateShopStatus(id, status);
    }
} 