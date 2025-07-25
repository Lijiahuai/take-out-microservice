package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.ProductLike;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品点赞Mapper接口
 */
@Mapper
@Repository
public interface ProductLikeMapper {
    
    /**
     * 通过商品ID和用户ID获取点赞
     * @param productId 商品ID
     * @param userId 用户ID
     * @return 点赞
     */
    @Select("SELECT * FROM product_like WHERE product_id = #{productId} AND user_id = #{userId}")
    ProductLike getLikeByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);
    
    /**
     * 通过商品ID获取点赞列表
     * @param productId 商品ID
     * @return 点赞列表
     */
    @Select("SELECT * FROM product_like WHERE product_id = #{productId} ORDER BY create_time DESC")
    List<ProductLike> getLikesByProductId(@Param("productId") Long productId);
    
    /**
     * 通过用户ID获取点赞列表
     * @param userId 用户ID
     * @return 点赞列表
     */
    @Select("SELECT * FROM product_like WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<ProductLike> getLikesByUserId(@Param("userId") Long userId);
    
    /**
     * 创建点赞
     * @param like 点赞
     * @return 影响行数
     */
    @Insert("INSERT INTO product_like(product_id, user_id, create_time) " +
            "VALUES(#{productId}, #{userId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createLike(ProductLike like);
    
    /**
     * 删除点赞
     * @param productId 商品ID
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM product_like WHERE product_id = #{productId} AND user_id = #{userId}")
    int deleteLike(@Param("productId") Long productId, @Param("userId") Long userId);
    
    /**
     * 获取商品点赞数量
     * @param productId 商品ID
     * @return 点赞数量
     */
    @Select("SELECT COUNT(*) FROM product_like WHERE product_id = #{productId}")
    Integer getLikeCount(@Param("productId") Long productId);
    
    /**
     * 检查用户是否点赞过商品
     * @param productId 商品ID
     * @param userId 用户ID
     * @return 是否点赞过
     */
    @Select("SELECT COUNT(*) > 0 FROM product_like WHERE product_id = #{productId} AND user_id = #{userId}")
    Boolean hasLiked(@Param("productId") Long productId, @Param("userId") Long userId);
} 