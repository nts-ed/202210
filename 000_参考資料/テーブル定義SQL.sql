USE database_name;    #自分のデータベースの名前


# t_user テーブル
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
`user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '社員ID',
`user_name` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '社員名',
`password` VARCHAR(50) DEFAULT '' NOT NULL COMMENT 'パスワード',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` INT DEFAULT 0 COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` INT DEFAULT 0 COMMENT '更新者id',
PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT 10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# m_unit テーブル
DROP TABLE IF EXISTS `m_unit`;
CREATE TABLE IF NOT EXISTS `m_unit` (
`unit_id` INT(11) NOT NULL COMMENT '単位ID',
`name` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '単位名称',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` INT(11) DEFAULT 0 COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` INT(11) DEFAULT 0 COMMENT '更新者id',
PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# t_stock テーブル
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE IF NOT EXISTS `t_stock` (
`id` VARCHAR(6) NOT NULL COMMENT '在庫商品ID',
`name` VARCHAR(50) DEFAULT '' NOT NULL COMMENT '在庫商品名称',
`unit_id` INT(11) DEFAULT 0 NOT NULL COMMENT '単位ID',
`stock_num` INT(11) DEFAULT 0 NOT NULL COMMENT '在庫数量',
`remarks` VARCHAR(200) DEFAULT '' COMMENT '備考',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` INT(11) DEFAULT 0 COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` INT(11) DEFAULT 0 COMMENT '更新者id',
`version` SMALLINT DEFAULT 1 COMMENT '楽観鎖',
PRIMARY KEY (`id`) USING BTREE,
CONSTRAINT fk_t_stock_unit_id FOREIGN KEY (unit_id) REFERENCES m_unit(unit_id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;


# t_stock_io テーブル
DROP TABLE IF EXISTS `t_stock_io`;
CREATE TABLE IF NOT EXISTS `t_stock_io` (
`id` VARCHAR(6) NOT NULL COMMENT '在庫商品ID',
`in_out_no` INT(5) DEFAULT 0 NOT NULL COMMENT '入出庫回数',
`io_type` INT(1) NOT NULL COMMENT '入出庫タイプ',
`io_num` INT(11) DEFAULT 0 COMMENT '入出庫数量',
`remarks` VARCHAR(200) DEFAULT '' COMMENT '備考',
`del_flg` TINYINT(1) DEFAULT 0 COMMENT '削除フラグ',
`create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
`create_user` INT(11) DEFAULT 0 COMMENT '作成者id',
`update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
`update_user` INT(11) DEFAULT 0 COMMENT '更新者id',
`version` SMALLINT DEFAULT 1 COMMENT '楽観鎖',
PRIMARY KEY (`id`, `in_out_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT = Dynamic;

