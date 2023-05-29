CREATE TABLE `tb_user` (
                           `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                           `username` varchar(30) DEFAULT NULL,
                           `password` varchar(30) DEFAULT NULL,
                           `age` int(10) unsigned DEFAULT NULL,
                           `create_by` varchar(30) NOT NULL,
                           `update_by` varchar(30) NOT NULL,
                           `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                           `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tb_user0` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `username` varchar(30) DEFAULT NULL,
                            `password` varchar(30) DEFAULT NULL,
                            `age` int(10) unsigned DEFAULT NULL,
                            `create_by` varchar(30) NOT NULL,
                            `update_by` varchar(30) NOT NULL,
                            `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                            `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tb_user1` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `username` varchar(30) DEFAULT NULL,
                            `password` varchar(30) DEFAULT NULL,
                            `age` int(10) unsigned DEFAULT NULL,
                            `create_by` varchar(30) NOT NULL,
                            `update_by` varchar(30) NOT NULL,
                            `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                            `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tb_user2` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `username` varchar(30) DEFAULT NULL,
                            `password` varchar(30) DEFAULT NULL,
                            `age` int(10) unsigned DEFAULT NULL,
                            `create_by` varchar(30) NOT NULL,
                            `update_by` varchar(30) NOT NULL,
                            `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
                            `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;