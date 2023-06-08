CREATE TABLE `tb_school_fraction_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `year` varchar(255) DEFAULT NULL COMMENT '年份(不要用数字)',
  `fraction` varchar(255) DEFAULT NULL COMMENT '分数',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `type` varchar(255) DEFAULT NULL COMMENT '分类',
  `primary_classification` varchar(255) DEFAULT NULL COMMENT '一级分类',
  `method` varchar(255) DEFAULT NULL COMMENT '统招/或者单招/其他',
  `speciality` varchar(255) DEFAULT NULL COMMENT '专业(工科/理工科/文科/国家专项)',
  `source_text` longtext COMMENT '数据json或者html',
  `school` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='大学分数';



CREATE TABLE `tb_base_data_dic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `pid_dic_uuid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '父级uuid',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `field_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '使用该数据的字段名称',
  `mnemonic_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '助记码',
  `sorting` int DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `bis_custom` bit(1) DEFAULT b'0' COMMENT '是否自定义',
  `bis_tree` bit(1) DEFAULT b'0' COMMENT '是否树形结构',
  `bis_delete` bit(1) DEFAULT b'0' COMMENT '是否删除',
  `bis_addible` bit(1) DEFAULT NULL COMMENT '顶级节点可设置',
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index` (`uuid`) USING BTREE,
  KEY `index_field` (`field_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='数据字典';
