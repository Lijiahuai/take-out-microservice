package com.takeout.auth.mapper;

import com.takeout.auth.entity.Admin;
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
     * 通过管理员用户名查询管理员
     * @param username 用户名
     * @return 管理员
     */
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin getAdminByUsername(@Param("username") String username);
    
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