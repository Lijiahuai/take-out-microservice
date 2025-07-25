-- 用户服务数据库表结构

-- 用户详细信息表
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '信息ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `gender` varchar(8) DEFAULT NULL COMMENT '性别: M/F/O',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `balance` decimal(10, 2) DEFAULT 0.00 COMMENT '余额',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `x` int DEFAULT 5000 COMMENT '模拟坐标X(范围1-10000)',
  `y` int DEFAULT 5000 COMMENT '模拟坐标Y(范围1-10000)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`) COMMENT '用户ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户详细信息表';

-- 插入测试数据
INSERT INTO user_info (user_id, nickname, real_name, phone, gender, avatar, balance, birthday, address, x, y, remark) VALUES 
(1, '测试用户', '张三', '13800138000', 'M', 'https://randomuser.me/api/portraits/men/1.jpg', 100.00, '1990-01-01 00:00:00', '北京市朝阳区', 5000, 5000, '测试账号'); 