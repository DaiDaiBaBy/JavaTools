package com.zhoufu.springbootasyncthreadpoolbatchlist.service;

import com.zhoufu.springbootasyncthreadpoolbatchlist.entity.SourceTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zf
 * @since 2023-05-30
 */
public interface SourceTableService extends IService<SourceTable> {

    void batchDealList(List<SourceTable> list);
}
