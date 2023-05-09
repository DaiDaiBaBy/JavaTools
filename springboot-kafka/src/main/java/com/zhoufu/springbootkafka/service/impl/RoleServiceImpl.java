package com.zhoufu.springbootkafka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoufu.springbootkafka.mapper.RoleMapper;
import com.zhoufu.springbootkafka.model.entity.RoleEntity;
import com.zhoufu.springbootkafka.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zf
 * @since 2021-02-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

}
