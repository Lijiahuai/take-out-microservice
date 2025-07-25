-- 购物车相关表结构

-- 购物车表
CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `shop_id` bigint NOT NULL COMMENT '店铺ID',
  `shop_name` varchar(100) NOT NULL COMMENT '店铺名称',
  `total_quantity` int NOT NULL DEFAULT '0' COMMENT '商品总数量',
  `total_amount` decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '商品总金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_shop` (`user_id`, `shop_id`) COMMENT '用户店铺唯一索引',
  KEY `idx_user_id` (`user_id`) COMMENT '用户ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- 购物车项表
CREATE TABLE IF NOT EXISTS `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `cart_id` bigint NOT NULL COMMENT '购物车ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `shop_id` bigint NOT NULL COMMENT '店铺ID',
  `dish_id` bigint NOT NULL COMMENT '菜品ID',
  `dish_name` varchar(64) NOT NULL COMMENT '菜品名称',
  `dish_image` varchar(255) DEFAULT NULL COMMENT '菜品图片',
  `price` decimal(10, 2) NOT NULL COMMENT '菜品单价',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '购买数量',
  `subtotal` decimal(10, 2) NOT NULL COMMENT '小计金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_cart_id` (`cart_id`) COMMENT '购物车ID索引',
  KEY `idx_user_id` (`user_id`) COMMENT '用户ID索引',
  KEY `idx_shop_id` (`shop_id`) COMMENT '店铺ID索引',
  KEY `idx_dish_id` (`dish_id`) COMMENT '菜品ID索引',
  UNIQUE KEY `uk_cart_dish` (`cart_id`, `dish_id`) COMMENT '购物车菜品唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车项表'; 