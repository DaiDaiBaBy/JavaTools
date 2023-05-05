package com.zhoufu.springbootresult.controller;

import com.zhoufu.springbootresult.domain.ProductInfoVo;
import com.zhoufu.springbootresult.domain.ResultVo;
import com.zhoufu.springbootresult.entity.ProductInfo;
import com.zhoufu.springbootresult.service.NotControllerResponseAdvice;
import com.zhoufu.springbootresult.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : DemoController
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:20
 * @Description : 控制器
 */
@RestController
@RequestMapping("/demo/product-info")
public class DemoController {
    @Autowired
    private ProductInfoService productInfoService;

    @PostMapping("/findByVo")
    public ResultVo findByVo(@Validated ProductInfoVo vo) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(vo, productInfo);
        return new ResultVo(productInfoService.getOne(productInfo));
    }

    @GetMapping("/health")
    @NotControllerResponseAdvice  // 不需要包装的方法上加上注解
    public String health() {
        return "success";
    }
}
