package com.takeout.user.mapper;

import com.takeout.user.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 用户信息Mapper接口
 */
@Mapper
@Repository
public interface UserInfoMapper {
    
    /**
     * 通过用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user_info WHERE user_id = #{userId}")
    UserInfo getUserInfoByUserId(@Param("userId") Long userId);
    
    /**
     * 创建用户信息
     * @param userInfo 用户信息
     * @return 影响行数
     */
    @Insert("INSERT INTO user_info(user_id, nickname, real_name, phone, gender, avatar, balance, birthday, address, x, y, remark, create_time, update_time) " +
            "VALUES(#{userId}, #{nickname}, #{realName}, #{phone}, #{gender}, #{avatar}, #{balance}, #{birthday}, #{address}, #{x}, #{y}, #{remark}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createUserInfo(UserInfo userInfo);
    
    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 影响行数
     */
    @Update("UPDATE user_info SET " +
            "nickname = #{nickname}, " +
            "real_name = #{realName}, " +
            "phone = #{phone}, " +
            "gender = #{gender}, " +
            "avatar = #{avatar}, " +
            "balance = #{balance}, " +
            "birthday = #{birthday}, " +
            "address = #{address}, " +
            "x = #{x}, " +
            "y = #{y}, " +
            "remark = #{remark}, " +
            "update_time = NOW() " +
            "WHERE user_id = #{userId}")
    int updateUserInfo(UserInfo userInfo);
} 