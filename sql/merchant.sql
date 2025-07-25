CREATE TABLE merchant
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商家ID',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '商家登录用户名',
    password    VARCHAR(100) NOT NULL COMMENT '商家密码',
    name        VARCHAR(50) COMMENT '商家姓名',
    phone       VARCHAR(20) COMMENT '商家电话',
    email       VARCHAR(100) COMMENT '商家邮箱',
    shop_id     BIGINT COMMENT '关联店铺ID',
    status      TINYINT  DEFAULT 1 COMMENT '状态（0禁用，1启用）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);


CREATE TABLE shop
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '店铺ID',
    name        VARCHAR(100) NOT NULL COMMENT '店铺名称',
    address     VARCHAR(255) COMMENT '店铺地址',
    location_x  int      default 5000 COMMENT '经度',
    location_y  int      default 5000 COMMENT '纬度',
    description TEXT COMMENT '店铺描述',
    merchant_id BIGINT       NOT NULL COMMENT '所属商家ID',
    status      TINYINT  DEFAULT 1 COMMENT '营业状态（0关闭，1营业）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_shop_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (id)
);
CREATE TABLE product
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    name        VARCHAR(100)   NOT NULL COMMENT '商品名称',
    description TEXT COMMENT '商品描述',
    price       DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
    image_url   VARCHAR(255) COMMENT '商品图片地址',
    shop_id     BIGINT         NOT NULL COMMENT '所属店铺ID',
    status      TINYINT  DEFAULT 1 COMMENT '是否上架（0下架，1上架）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_product_shop FOREIGN KEY (shop_id) REFERENCES shop (id)
);
CREATE TABLE inventory
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '库存记录ID',
    product_id   BIGINT NOT NULL COMMENT '对应商品ID',
    quantity     INT      DEFAULT 0 COMMENT '库存数量',
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES product (id)
);
CREATE TABLE product_review
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '评价ID',
    product_id  BIGINT  NOT NULL COMMENT '商品ID',
    user_id     BIGINT  NOT NULL COMMENT '评价用户ID',
    rating      TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5) COMMENT '评分（1~5）',
    comment     TEXT COMMENT '评价内容',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_review_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES user (id)
);
CREATE TABLE product_like
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '点赞ID',
    product_id  BIGINT NOT NULL COMMENT '商品ID',
    user_id     BIGINT NOT NULL COMMENT '点赞用户ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
    CONSTRAINT fk_like_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_like_user FOREIGN KEY (user_id) REFERENCES user (id),
    UNIQUE KEY uniq_product_user (product_id, user_id) -- 避免一个用户给同一个商品重复点赞
);
