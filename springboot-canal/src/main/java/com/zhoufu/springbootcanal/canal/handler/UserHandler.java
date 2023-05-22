package com.zhoufu.springbootcanal.canal.handler;

import com.zhoufu.springbootcanal.canal.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @ClassName : UserHandler
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:15
 * @Description : 订阅数据库的CRUD操作
 */
@Slf4j
@Component
@CanalTable(value = "t_user")
public class UserHandler implements EntryHandler<User> {

    @Override
    public void insert(User user) {
        log.info("insert message  {}", user);
    }

    @Override
    public void update(User before, User after) {
        log.info("update before {} ", before);
        log.info("update after {}", after);
    }

    @Override
    public void delete(User user) {
        log.info("delete  {}", user);
    }

}
