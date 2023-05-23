package com.zhoufu.springbootqiniucloud.modules.common.dto.input;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p> 应用基础传入参数 </p>
 */
@ApiModel(description = "应用基础传入参数")
@Data
public class BaseInput {
    @ApiModelProperty(value = "令牌")
    @JSONField(name = "token")
    private String token;
}
