USE database_name;    #自分のデータベースの名前

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


# t_user テーブル
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
`user_id` VARCHAR(11) NOT NULL COMMENT '社員ID',
`user_name` VARCHAR(50) NOT NULL COMMENT '社員名',
`password` VARCHAR(50) NOT NULL COMMENT 'パスワード',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` VARCHAR(11) DEFAULT '' COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` VARCHAR(11) DEFAULT '' COMMENT '更新者id',
PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# m_unit テーブル
DROP TABLE IF EXISTS `m_unit`;
CREATE TABLE IF NOT EXISTS `m_unit` (
`unit_id` INT(11) NOT NULL COMMENT '単位ID',
`name` VARCHAR(50) NOT NULL COMMENT '単位名称',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` VARCHAR(11) DEFAULT '' COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` VARCHAR(11) DEFAULT '' COMMENT '更新者id',
PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# t_stock テーブル
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE IF NOT EXISTS `t_stock` (
`id` VARCHAR(6) NOT NULL COMMENT '在庫商品ID',
`name` VARCHAR(50) NOT NULL COMMENT '在庫商品名称',
`unit_id` INT(11) NOT NULL COMMENT '単位ID',
`stock_num` INT(11) DEFAULT 0 NOT NULL COMMENT '在庫数量',
`remarks` VARCHAR(200) DEFAULT '' COMMENT '備考',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` VARCHAR(11) DEFAULT '' COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` VARCHAR(11) DEFAULT '' COMMENT '更新者id',
`version` SMALLINT DEFAULT 1 COMMENT '楽観鎖',
PRIMARY KEY (`id`) USING BTREE,
CONSTRAINT fk_t_stock_unit_id FOREIGN KEY (unit_id) REFERENCES m_unit(unit_id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# t_stock_io テーブル
DROP TABLE IF EXISTS `t_stock_io`;
CREATE TABLE IF NOT EXISTS `t_stock_io` (
`id` VARCHAR(6) NOT NULL COMMENT '在庫商品ID',
`in_out_no` INT(5) DEFAULT 0 NOT NULL COMMENT '入出庫回数',
`io_type` TINYINT(1) NOT NULL COMMENT '入出庫タイプ',
`io_num` INT(11) DEFAULT 0 COMMENT '入出庫数量',
`remarks` VARCHAR(200) DEFAULT '' COMMENT '備考',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` VARCHAR(11) DEFAULT '' COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` VARCHAR(11) DEFAULT '' COMMENT '更新者id',
`version` SMALLINT DEFAULT 1 COMMENT '楽観鎖',
PRIMARY KEY (`id`, `in_out_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;



# m_code テーブル
DROP TABLE IF EXISTS `m_code`;
CREATE TABLE IF NOT EXISTS `m_code` (
`code_id` INT(11) NOT NULL COMMENT 'コードID',
`code_id1` INT(11) NOT NULL COMMENT 'コードID1',
`name` VARCHAR(50) NOT NULL COMMENT 'コード名称',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` VARCHAR(11) DEFAULT '' COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` VARCHAR(11) DEFAULT '' COMMENT '更新者id',
`version` SMALLINT DEFAULT 1 COMMENT '楽観鎖',
PRIMARY KEY (`code_id`, `code_id1`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;



BEGIN;
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (1, '個', 0, '2022-10-11 15:41:03', '', '2022-10-11 15:41:03', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (2, '台', 0, '2022-10-11 15:41:16', '', '2022-10-11 15:41:16', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (3, '本', 0, '2022-10-11 15:41:38', '', '2022-10-11 15:41:38', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (4, '冊', 0, '2022-10-11 15:42:09', '', '2022-10-11 15:42:09', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (5, '皿', 0, '2022-10-11 15:42:21', '', '2022-10-11 15:42:21', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (6, '箱', 0, '2022-10-11 15:42:50', '', '2022-10-11 15:42:50', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (7, '足', 0, '2022-10-11 15:44:17', '', '2022-10-11 15:44:17', '');
INSERT INTO `m_unit` (`unit_id`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`) VALUES (8, '件', 0, '2022-10-11 15:44:59', '', '2022-10-11 15:44:59', '');
INSERT INTO `m_code` (`code_id`, `code_id1`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`, `version`) VALUES (1, 2, '購買発注入庫', 0, '2022-10-12 17:22:51', '', '2022-10-12 17:22:51', '', 1);
INSERT INTO `m_code` (`code_id`, `code_id1`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`, `version`) VALUES (3, 4, '仕入返品', 0, '2022-10-12 17:23:04', '', '2022-10-12 17:23:04', '', 1);
INSERT INTO `m_code` (`code_id`, `code_id1`, `name`, `del_flg`, `create_date`, `create_user`, `update_date`, `update_user`, `version`) VALUES (5, 6, '出庫', 0, '2022-10-12 17:23:15', '', '2022-10-12 17:23:15', '', 1);
COMMIT;


SET FOREIGN_KEY_CHECKS = 1;

