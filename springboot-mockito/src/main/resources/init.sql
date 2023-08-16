# sql初始化脚本
CREATE TABLE `T_ORDER` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `GOODS_ID` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `T_STORE` (
                           `ID` int(11) NOT NULL AUTO_INCREMENT,
                           `CURRENT_COUNT` int(11) DEFAULT NULL COMMENT '剩余库存',
                           `GOODS_ID` varchar(255) DEFAULT NULL COMMENT '商品ID',
                           PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;