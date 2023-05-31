package com.zhoufu.springbootasyncthreadpoolbatchlist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author zf
 * @since 2023-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SourceTable implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String address;


}
