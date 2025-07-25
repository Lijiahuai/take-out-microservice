package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.Inventory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存Mapper接口
 */
@Mapper
@Repository
public interface InventoryMapper {
    
    /**
     * 通过商品ID获取库存
     * @param productId 商品ID
     * @return 库存
     */
    @Select("SELECT * FROM inventory WHERE product_id = #{productId}")
    Inventory getInventoryByProductId(@Param("productId") Long productId);
    
    /**
     * 通过商品ID列表批量获取库存
     * @param productIds 商品ID列表
     * @return 库存列表
     */
    @Select("<script>" +
            "SELECT * FROM inventory WHERE product_id IN " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Inventory> getInventoriesByProductIds(@Param("list") List<Long> productIds);
    
    /**
     * 创建库存
     * @param inventory 库存
     * @return 影响行数
     */
    @Insert("INSERT INTO inventory(product_id, quantity, last_updated) " +
            "VALUES(#{productId}, #{quantity}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createInventory(Inventory inventory);
    
    /**
     * 更新库存
     * @param inventory 库存
     * @return 影响行数
     */
    @Update("UPDATE inventory SET quantity = #{quantity}, last_updated = NOW() " +
            "WHERE product_id = #{productId}")
    int updateInventory(Inventory inventory);
    
    /**
     * 减少库存
     * @param productId 商品ID
     * @param quantity 减少数量
     * @return 影响行数
     */
    @Update("UPDATE inventory SET quantity = quantity - #{quantity}, last_updated = NOW() " +
            "WHERE product_id = #{productId} AND quantity >= #{quantity}")
    int decreaseInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);
    
    /**
     * 增加库存
     * @param productId 商品ID
     * @param quantity 增加数量
     * @return 影响行数
     */
    @Update("UPDATE inventory SET quantity = quantity + #{quantity}, last_updated = NOW() " +
            "WHERE product_id = #{productId}")
    int increaseInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);
    
    /**
     * 删除库存
     * @param productId 商品ID
     * @return 影响行数
     */
    @Delete("DELETE FROM inventory WHERE product_id = #{productId}")
    int deleteInventory(@Param("productId") Long productId);
} 