package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.Shop;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 店铺Mapper接口
 */
@Mapper
@Repository
public interface ShopMapper {
    
    /**
     * 获取所有店铺
     * @return 店铺列表
     */
    @Select("SELECT * FROM shop ORDER BY id DESC")
    List<Shop> getAllShops();
    
    /**
     * 通过ID获取店铺
     * @param id 店铺ID
     * @return 店铺
     */
    @Select("SELECT * FROM shop WHERE id = #{id}")
    Shop getShopById(@Param("id") Long id);
    
    /**
     * 创建店铺
     * @param shop 店铺
     * @return 影响行数
     */
    @Insert("INSERT INTO shop(name, image, introduction, address, phone, status, opening_hours, rating, create_time, update_time) " +
            "VALUES(#{name}, #{image}, #{introduction}, #{address}, #{phone}, #{status}, #{openingHours}, 0, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createShop(Shop shop);
    
    /**
     * 更新店铺
     * @param shop 店铺
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE shop SET update_time = NOW() " +
            "<if test='name != null'>, name = #{name}</if>" +
            "<if test='image != null'>, image = #{image}</if>" +
            "<if test='introduction != null'>, introduction = #{introduction}</if>" +
            "<if test='address != null'>, address = #{address}</if>" +
            "<if test='phone != null'>, phone = #{phone}</if>" +
            "<if test='status != null'>, status = #{status}</if>" +
            "<if test='openingHours != null'>, opening_hours = #{openingHours}</if>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateShop(Shop shop);
    
    /**
     * 更新店铺评分
     * @param id 店铺ID
     * @param rating 评分
     * @return 影响行数
     */
    @Update("UPDATE shop SET rating = #{rating}, update_time = NOW() WHERE id = #{id}")
    int updateShopRating(@Param("id") Long id, @Param("rating") Double rating);
} 