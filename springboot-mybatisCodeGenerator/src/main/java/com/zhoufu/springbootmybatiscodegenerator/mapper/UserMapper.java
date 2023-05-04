package com.zhoufu.springbootmybatiscodegenerator.mapper;

import com.zhoufu.springbootmybatiscodegenerator.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZhouFu
 * @since 2023-04-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
