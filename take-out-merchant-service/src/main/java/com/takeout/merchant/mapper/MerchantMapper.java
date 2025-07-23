package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.Merchant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 商家Mapper接口
 */
@Mapper
@Repository
public interface MerchantMapper {
    
    /**
     * 通过ID获取商家
     * @param id 商家ID
     * @return 商家
     */
    @Select("SELECT * FROM merchant WHERE id = #{id}")
    Merchant getMerchantById(@Param("id") Long id);
    
    /**
     * 通过用户名获取商家
     * @param username 用户名
     * @return 商家
     */
    @Select("SELECT * FROM merchant WHERE username = #{username}")
    Merchant getMerchantByUsername(@Param("username") String username);
    
    /**
     * 通过店铺ID获取商家
     * @param shopId 店铺ID
     * @return 商家
     */
    @Select("SELECT * FROM merchant WHERE shop_id = #{shopId}")
    Merchant getMerchantByShopId(@Param("shopId") Long shopId);
    
    /**
     * 创建商家
     * @param merchant 商家
     * @return 影响行数
     */
    @Insert("INSERT INTO merchant(username, password, name, phone, email, shop_id, status, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{name}, #{phone}, #{email}, #{shopId}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createMerchant(Merchant merchant);
    
    /**
     * 更新商家
     * @param merchant 商家
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE merchant SET update_time = NOW() " +
            "<if test='password != null'>, password = #{password}</if>" +
            "<if test='name != null'>, name = #{name}</if>" +
            "<if test='phone != null'>, phone = #{phone}</if>" +
            "<if test='email != null'>, email = #{email}</if>" +
            "<if test='status != null'>, status = #{status}</if>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateMerchant(Merchant merchant);
} 