package com.zhoufu.springbootcanal.canal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @ClassName : CanalMsg
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:16
 * @Description : canal 消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("canal消息体")
public class CanalMsg {

    @ApiModelProperty(value = "id")
    private Integer id;

}
