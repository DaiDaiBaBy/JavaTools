package com.zhoufu.springbootasyncthreadpoolbatchlist.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoufu.springbootasyncthreadpoolbatchlist.entity.SourceTable;
import com.zhoufu.springbootasyncthreadpoolbatchlist.entity.TargetTable;
import com.zhoufu.springbootasyncthreadpoolbatchlist.mapper.SourceTableMapper;
import com.zhoufu.springbootasyncthreadpoolbatchlist.service.SourceTableService;
import com.zhoufu.springbootasyncthreadpoolbatchlist.service.TargetTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zf
 * @since 2023-05-30
 */
@Service
@Slf4j
public class SourceTableServiceImpl extends ServiceImpl<SourceTableMapper, SourceTable> implements SourceTableService {

    @Autowired
    private TargetTableService targetTableService;
    @Override
    @Async("MyExecutor")
    public void batchDealList(List<SourceTable> list) {
        log.info("正在处理业务batchDealList：{},{}", Thread.currentThread().getName(),  UUID.randomUUID().toString());
        try {
          //  sleep(1000);  // 加入一次分批处理List需要1秒钟

            // 处理数据 存入另一张表
            List<TargetTable> targetTableList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(list)) {
                // 做个优化， 避免stream里频繁的创建new对象，导致oom
                // 可以考虑提取成一个独立的方法，或者使用方法引用来避免创建新对象。
                targetTableList = list.stream().map(this::toTargetTable).collect(Collectors.toList());
            }

            targetTableService.saveOrUpdateBatch(targetTableList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private TargetTable toTargetTable(SourceTable sourceTable) {
        return new TargetTable(sourceTable.getId(), sourceTable.getName(), sourceTable.getAddress());
    }
}
