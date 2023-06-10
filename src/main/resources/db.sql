CREATE TABLE `tb_school_fraction_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `year` varchar(255) DEFAULT NULL COMMENT '年份(不要用数字)',
  `fraction` varchar(255) DEFAULT NULL COMMENT '分数',
  `max_score` varchar(255) DEFAULT NULL COMMENT '录取最高分数',
  `min_score` varchar(255) DEFAULT NULL COMMENT '录取最低分数',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `type` varchar(255) DEFAULT NULL COMMENT '分类',
  `primary_classification` varchar(255) DEFAULT NULL COMMENT '一级分类',
  `method` varchar(255) DEFAULT NULL COMMENT '统招/或者单招/其他',
  `speciality` varchar(255) DEFAULT NULL COMMENT '专业(工科/理工科/文科/国家专项)',
  `source_text` longtext COMMENT '数据json或者html',
  `school` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `type_enum` varchar(100) DEFAULT NULL COMMENT '枚举分类',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1444 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='大学分数';

CREATE TABLE `tb_university_info` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `uuid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
                                    `name` varchar(255) DEFAULT NULL COMMENT '学校名称',
                                    `type` varchar(255) DEFAULT NULL COMMENT '院校类型',
                                    `tag` varchar(255) DEFAULT NULL COMMENT 'tag',
                                    `subordinate` varchar(255) DEFAULT NULL COMMENT '院校隶属',
                                    `location` varchar(255) DEFAULT NULL COMMENT '所在地区',
                                    `official_website` varchar(255) DEFAULT NULL COMMENT '官网',
                                    `special_admission` varchar(255) DEFAULT NULL COMMENT '特殊招生',
                                    `ranking` varchar(255) DEFAULT NULL COMMENT '学校排名',
                                    `star_rating` varchar(255) DEFAULT NULL COMMENT '星级',
                                    `total_score` varchar(255) DEFAULT NULL COMMENT '总分',
                                    `scientific_research_score` varchar(255) DEFAULT NULL COMMENT '科学研究(分数)',
                                    `personnel_training_score` varchar(255) DEFAULT NULL COMMENT '人才培养(分数)',
                                    `comprehensive_reputation` varchar(255) DEFAULT NULL COMMENT '综合声誉(分数)',
                                    `educational_level` varchar(255) DEFAULT NULL COMMENT '办学层次',
                                    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
                                    `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE KEY `index` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='大学信息';

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

CREATE TABLE `tb_base_area` (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
                              `pid_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '父级编码',
                              `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
                              `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
                              `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `index_par_key` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='省市区表';


CREATE TABLE `tb_common_school_fraction_info` (
                                                `id` int NOT NULL AUTO_INCREMENT,
                                                `uuid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
                                                `year` varchar(255) DEFAULT NULL COMMENT '年份(不要用数字)',
                                                `fraction` varchar(255) DEFAULT NULL COMMENT '分数',
                                                `max_score` varchar(255) DEFAULT NULL COMMENT '录取最高分数',
                                                `min_score` varchar(255) DEFAULT NULL COMMENT '录取最低分数',
                                                `province` varchar(255) DEFAULT NULL COMMENT '省',
                                                `type` varchar(255) DEFAULT NULL COMMENT '分类',
                                                `number` varchar(255) DEFAULT NULL COMMENT '批次',
                                                `primary_classification` varchar(255) DEFAULT NULL COMMENT '一级分类',
                                                `method` varchar(255) DEFAULT NULL COMMENT '统招/或者单招/其他',
                                                `speciality` varchar(255) DEFAULT NULL COMMENT '专业(工科/理工科/文科/国家专项)',
                                                `source_text` longtext COMMENT '数据json或者html',
                                                `school` varchar(255) DEFAULT NULL COMMENT '学校名称',
                                                `type_enum` varchar(100) DEFAULT NULL COMMENT '枚举分类',
                                                `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
                                                `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间，记录变化后会自动更新时间戳',
                                                PRIMARY KEY (`id`) USING BTREE,
                                                UNIQUE KEY `index` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='大学高考分数(普通)';

