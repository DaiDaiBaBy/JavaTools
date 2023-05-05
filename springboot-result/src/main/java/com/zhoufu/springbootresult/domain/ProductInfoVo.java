package com.zhoufu.springbootresult.domain;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName : ProductInfoVo
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:18
 * @Description : 测试用
 */
@Data
public class ProductInfoVo {
    // 商品名称
    @NotNull(message = "商品名称不允许为空")
    private String productName;
    // 商品价格
    @Min(value = 0, message = "商品价格不允许为负数")
    private BigDecimal productPrice;
    // 上架状态
    private Integer productStatus;
}
