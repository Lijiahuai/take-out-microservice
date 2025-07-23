package com.takeout.merchant.mapper;

import com.takeout.merchant.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类Mapper接口
 */
@Mapper
@Repository
public interface CategoryMapper {
    
    /**
     * 通过店铺ID获取分类列表
     * @param shopId 店铺ID
     * @return 分类列表
     */
    @Select("SELECT * FROM category WHERE shop_id = #{shopId} ORDER BY sort, id")
    List<Category> getCategoriesByShopId(@Param("shopId") Long shopId);
    
    /**
     * 通过ID获取分类
     * @param id 分类ID
     * @return 分类
     */
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category getCategoryById(@Param("id") Long id);
    
    /**
     * 创建分类
     * @param category 分类
     * @return 影响行数
     */
    @Insert("INSERT INTO category(name, icon, sort, status, shop_id, create_time, update_time) " +
            "VALUES(#{name}, #{icon}, #{sort}, #{status}, #{shopId}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCategory(Category category);
    
    /**
     * 更新分类
     * @param category 分类
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE category SET update_time = NOW() " +
            "<if test='name != null'>, name = #{name}</if>" +
            "<if test='icon != null'>, icon = #{icon}</if>" +
            "<if test='sort != null'>, sort = #{sort}</if>" +
            "<if test='status != null'>, status = #{status}</if>" +
            "WHERE id = #{id} AND shop_id = #{shopId}" +
            "</script>")
    int updateCategory(Category category);
    
    /**
     * 删除分类
     * @param id 分类ID
     * @param shopId 店铺ID
     * @return 影响行数
     */
    @Delete("DELETE FROM category WHERE id = #{id} AND shop_id = #{shopId}")
    int deleteCategory(@Param("id") Long id, @Param("shopId") Long shopId);
} 