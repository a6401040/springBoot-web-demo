DROP DATABASE IF EXISTS product_master;
CREATE DATABASE product_master;
CREATE TABLE product_master.t_product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO product_master.t_product (name, price) VALUES('master', '1');


DROP DATABASE IF EXISTS product_slave;
CREATE DATABASE product_slave;
CREATE TABLE product_slave.t_product(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO product_slave.t_product (name, price) VALUES('slave', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'sss', 1, NULL);
