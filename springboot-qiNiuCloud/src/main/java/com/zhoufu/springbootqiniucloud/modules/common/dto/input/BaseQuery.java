package com.zhoufu.springbootqiniucloud.modules.common.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  <p> 基类查询参数 </p>
 */
@ApiModel(description = "基类查询参数")
@Data
public class BaseQuery extends BasePageQuery {
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
}
