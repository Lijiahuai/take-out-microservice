-- 订单服务数据库表结构

-- 订单表
CREATE TABLE IF NOT EXISTS `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `shop_id` bigint NOT NULL COMMENT '店铺ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10, 2) NOT NULL COMMENT '实付金额',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态：0-待支付，1-已支付，2-配送中，3-已完成，4-已取消',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付方式：1-余额支付，2-微信支付，3-支付宝支付',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `address` varchar(255) DEFAULT NULL COMMENT '配送地址',
  `receiver` varchar(64) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`) COMMENT '订单编号唯一索引',
  KEY `idx_user_id` (`user_id`) COMMENT '用户ID索引',
  KEY `idx_merchant_id` (`merchant_id`) COMMENT '商家ID索引',
  KEY `idx_shop_id` (`shop_id`) COMMENT '店铺ID索引',
  KEY `idx_status` (`status`) COMMENT '订单状态索引',
  KEY `idx_create_time` (`create_time`) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单项表
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `dish_id` bigint NOT NULL COMMENT '菜品ID',
  `dish_name` varchar(64) NOT NULL COMMENT '菜品名称',
  `dish_image` varchar(255) DEFAULT NULL COMMENT '菜品图片',
  `price` decimal(10, 2) NOT NULL COMMENT '菜品单价',
  `quantity` int NOT NULL COMMENT '购买数量',
  `subtotal` decimal(10, 2) NOT NULL COMMENT '小计金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) COMMENT '订单ID索引',
  KEY `idx_dish_id` (`dish_id`) COMMENT '菜品ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项表';

-- 订单配送表
CREATE TABLE IF NOT EXISTS `order_delivery` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '配送ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `delivery_man` varchar(64) DEFAULT NULL COMMENT '配送员',
  `delivery_phone` varchar(20) DEFAULT NULL COMMENT '配送员电话',
  `start_time` datetime DEFAULT NULL COMMENT '开始配送时间',
  `finish_time` datetime DEFAULT NULL COMMENT '送达时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '配送状态：0-待配送，1-配送中，2-已送达',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_id` (`order_id`) COMMENT '订单ID唯一索引',
  KEY `idx_status` (`status`) COMMENT '配送状态索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单配送表';

-- 订单支付表
CREATE TABLE IF NOT EXISTS `order_payment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '支付ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `payment_no` varchar(64) DEFAULT NULL COMMENT '支付流水号',
  `pay_amount` decimal(10, 2) NOT NULL COMMENT '支付金额',
  `pay_type` tinyint NOT NULL COMMENT '支付方式：1-余额支付，2-微信支付，3-支付宝支付',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态：0-未支付，1-支付中，2-支付成功，3-支付失败',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_id` (`order_id`) COMMENT '订单ID唯一索引',
  KEY `idx_payment_no` (`payment_no`) COMMENT '支付流水号索引',
  KEY `idx_status` (`status`) COMMENT '支付状态索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单支付表'; 