package com.zhoufu.springbootresult.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName : ProductInfo
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:26
 * @Description :
 */
@Data
public class ProductInfo {
    private Integer productId;
    // 商品名称
    private String productName;
    // 商品价格
    private BigDecimal productPrice;
    // 上架状态
    private Integer productStatus;

    private String productDescription;
}
