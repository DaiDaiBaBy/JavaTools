package com.zhoufu.springbootexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import com.zhoufu.springbootexcel.domain.DemoData;

import java.util.Map;

/**
 * @ClassName : ExcelReadListener
 * @Author : ZhouFu
 * @Date: 2023/4/27 15:00
 * @Description : excel读取操作监听器
 */
public class ExcelReadListener extends AnalysisEventListener<DemoData> {

    /**
     * 一行一行读取excel内容   类似for处理
     * @param demoData
     * @param analysisContext
     */
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("excel内容：" + JSON.toJSONString(demoData));
        System.out.println(demoData.getSno());
        System.out.println(demoData.getSname());
        // 拿到excel信息， 可以继续执行后续处理逻辑
    }
    /**
     * 读取Excel表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("excel表头：" + headMap);
        // 拿到表头信息，  执行后续逻辑
    }
    /**
     * 读取完之后执行的逻辑
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("excel读取完成。。。");
    }
}
