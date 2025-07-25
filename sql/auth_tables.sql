-- 认证服务数据库表结构

-- 用户表
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码(MD5加密)',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) COMMENT '用户名唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 商家表
CREATE TABLE `merchant` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码(MD5加密)',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) COMMENT '用户名唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商家信息表';

-- 测试数据
-- 插入测试用户
INSERT INTO user (username, password, phone, email) VALUES 
('testuser', MD5('password123'), '13800138000', 'user@example.com');

-- 插入测试商家
INSERT INTO merchant (username, password, phone, email) VALUES 
('testmerchant', MD5('password123'), '13900139000', 'merchant@example.com');

-- 用户详细信息表
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '信息ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `gender` varchar(8) DEFAULT NULL COMMENT '性别: M/F/O',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `x` int DEFAULT NULL COMMENT '模拟坐标X(范围1-10000)',
  `y` int DEFAULT NULL COMMENT '模拟坐标Y(范围1-10000)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`) COMMENT '用户ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户详细信息表'; 