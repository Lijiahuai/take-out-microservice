package com.takeout.auth.mapper;

import com.takeout.auth.entity.Merchant;
import com.takeout.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * 认证Mapper接口
 */
@Mapper
@Repository
public interface AuthMapper {
    
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);
    
    /**
     * 通过商家用户名查询商家
     * @param username 用户名
     * @return 商家
     */
    @Select("SELECT * FROM merchant WHERE username = #{username}")
    Merchant getMerchantByUsername(@Param("username") String username);
    
    /**
     * 注册用户
     * @param user 用户信息
     * @return 影响行数
     */
    @Insert("INSERT INTO user(username, password, phone, email, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{phone}, #{email}, NOW(), NOW())")
    int registerUser(User user);
    
    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 存在数量
     */
    @Select("SELECT COUNT(*) FROM user WHERE username = #{username}")
    int checkUsernameExists(@Param("username") String username);
} 