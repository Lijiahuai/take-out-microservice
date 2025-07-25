package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品Mapper接口
 */
@Mapper
@Repository
public interface ProductMapper {
    
    /**
     * 获取所有商品
     * @return 商品列表
     */
    @Select("SELECT * FROM product ORDER BY id DESC")
    List<Product> getAllProducts();
    
    /**
     * 通过ID获取商品
     * @param id 商品ID
     * @return 商品
     */
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductById(@Param("id") Long id);
    
    /**
     * 通过店铺ID获取商品列表
     * @param shopId 店铺ID
     * @return 商品列表
     */
    @Select("SELECT * FROM product WHERE shop_id = #{shopId} ORDER BY id DESC")
    List<Product> getProductsByShopId(@Param("shopId") Long shopId);
    
    /**
     * 创建商品
     * @param product 商品
     * @return 影响行数
     */
    @Insert("INSERT INTO product(name, description, price, image_url, shop_id, status, create_time, update_time) " +
            "VALUES(#{name}, #{description}, #{price}, #{imageUrl}, #{shopId}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createProduct(Product product);
    
    /**
     * 更新商品
     * @param product 商品
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE product SET update_time = NOW() " +
            "<if test='name != null'>, name = #{name}</if>" +
            "<if test='description != null'>, description = #{description}</if>" +
            "<if test='price != null'>, price = #{price}</if>" +
            "<if test='imageUrl != null'>, image_url = #{imageUrl}</if>" +
            "<if test='status != null'>, status = #{status}</if>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateProduct(Product product);
    
    /**
     * 删除商品
     * @param id 商品ID
     * @return 影响行数
     */
    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteProduct(@Param("id") Long id);
} 