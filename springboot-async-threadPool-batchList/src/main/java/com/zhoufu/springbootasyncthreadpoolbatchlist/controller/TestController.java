package com.zhoufu.springbootasyncthreadpoolbatchlist.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.zhoufu.springbootasyncthreadpoolbatchlist.entity.SourceTable;
import com.zhoufu.springbootasyncthreadpoolbatchlist.service.SourceTableService;
import com.zhoufu.springbootasyncthreadpoolbatchlist.service.TargetTableService;
import com.zhoufu.springbootasyncthreadpoolbatchlist.threadConfig.ThreadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @ClassName : TestController
 * @Author : ZhouFu
 * @Date: 2023/5/30 17:46
 * @Description : 测试用
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private SourceTableService sourceTableService;

    @Autowired
    private TargetTableService targetTableService;

    @Autowired
    private ThreadConfig threadConfig;


    @GetMapping("doBatchParallelTes")
    public void doBatchParallelTes() {
        QueryWrapper<SourceTable> wrapper = new QueryWrapper<>();
        wrapper.lt("id",221000);
        List<SourceTable> sourceTables = sourceTableService.list(wrapper);
        System.out.println("总数据量：" + sourceTables.size());
        List<List<SourceTable>> allList = Lists.partition(sourceTables, 1000); // 每次100条的处理
        int batchNum = allList.size();
        StopWatch stopWatch = new StopWatch(); // 直观输出代码执行时间
        stopWatch.start();
        Executor threadConfigExecutor = threadConfig.getExecutor();
        List<CompletableFuture> results = new ArrayList<>();
        for (List<SourceTable> batchSourceTables :allList){
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                sourceTableService.batchDealList(batchSourceTables);
                return "";
            }, threadConfigExecutor);
            results.add(future);
        }
        CompletableFuture.allOf(results.toArray(results.toArray(new CompletableFuture[batchNum]))).join();
        stopWatch.stop();
        System.out.println("总用时"+stopWatch.getTotalTimeMillis()+"毫秒");
    }

}
