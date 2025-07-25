package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.ProductReview;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品评价Mapper接口
 */
@Mapper
@Repository
public interface ProductReviewMapper {
    
    /**
     * 通过ID获取评价
     * @param id 评价ID
     * @return 评价
     */
    @Select("SELECT * FROM product_review WHERE id = #{id}")
    ProductReview getReviewById(@Param("id") Long id);
    
    /**
     * 通过商品ID获取评价列表
     * @param productId 商品ID
     * @return 评价列表
     */
    @Select("SELECT * FROM product_review WHERE product_id = #{productId} ORDER BY create_time DESC")
    List<ProductReview> getReviewsByProductId(@Param("productId") Long productId);
    
    /**
     * 通过用户ID获取评价列表
     * @param userId 用户ID
     * @return 评价列表
     */
    @Select("SELECT * FROM product_review WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<ProductReview> getReviewsByUserId(@Param("userId") Long userId);
    
    /**
     * 创建评价
     * @param review 评价
     * @return 影响行数
     */
    @Insert("INSERT INTO product_review(product_id, user_id, rating, comment, create_time, update_time) " +
            "VALUES(#{productId}, #{userId}, #{rating}, #{comment}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createReview(ProductReview review);
    
    /**
     * 更新评价
     * @param review 评价
     * @return 影响行数
     */
    @Update("UPDATE product_review SET rating = #{rating}, comment = #{comment}, update_time = NOW() " +
            "WHERE id = #{id}")
    int updateReview(ProductReview review);
    
    /**
     * 删除评价
     * @param id 评价ID
     * @return 影响行数
     */
    @Delete("DELETE FROM product_review WHERE id = #{id}")
    int deleteReview(@Param("id") Long id);
    
    /**
     * 获取商品平均评分
     * @param productId 商品ID
     * @return 平均评分
     */
    @Select("SELECT AVG(rating) FROM product_review WHERE product_id = #{productId}")
    Double getAverageRating(@Param("productId") Long productId);
    
    /**
     * 获取商品评价数量
     * @param productId 商品ID
     * @return 评价数量
     */
    @Select("SELECT COUNT(*) FROM product_review WHERE product_id = #{productId}")
    Integer getReviewCount(@Param("productId") Long productId);
} 