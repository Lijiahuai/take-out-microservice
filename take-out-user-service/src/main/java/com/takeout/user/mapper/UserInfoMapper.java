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
    @Insert("INSERT INTO user_info(user_id, nickname, avatar, balance, gender, birthday, address, create_time, update_time) " +
            "VALUES(#{userId}, #{nickname}, #{avatar}, #{balance}, #{gender}, #{birthday}, #{address}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createUserInfo(UserInfo userInfo);
    
    /**
     * 更新用户信息
     * @param userInfo 用户信息
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE user_info SET update_time = NOW() " +
            "<if test='nickname != null'>, nickname = #{nickname}</if>" +
            "<if test='avatar != null'>, avatar = #{avatar}</if>" +
            "<if test='balance != null'>, balance = #{balance}</if>" +
            "<if test='gender != null'>, gender = #{gender}</if>" +
            "<if test='birthday != null'>, birthday = #{birthday}</if>" +
            "<if test='address != null'>, address = #{address}</if>" +
            "WHERE user_id = #{userId}" +
            "</script>")
    int updateUserInfo(UserInfo userInfo);
} 