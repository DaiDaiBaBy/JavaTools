//package com.zhoufu.springbootcanal.common;
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.client.CanalConnectors;
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import com.alibaba.otter.canal.protocol.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.net.InetSocketAddress;
//import java.util.Date;
//import java.util.List;
//
///**
// * @ClassName : CanalCommandLineRunner
// * @Author : ZhouFu
// * @Date: 2023/5/22 10:27
// * @Description : Canal监听程序
// *
// * 基于canal实现mysql binLog监听
// */
//@Component
//@Slf4j
//public class CanalCommandLineRunner implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//        //在canal部署的conf/canal.properties ip和端口信息
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11110), "example", "admin", "4ACFE3202A5FF5CF467898FC58AAB1D615029441");
//        try {
//            //打开连接
//            connector.connect();
//            //订阅数据库表,全部表q
//            // connector.subscribe(".*\\..*");
//            // 监听jes库中的字典表
//            connector.subscribe("jes.jes_dictionary");
//            //回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
//            connector.rollback();
//            while (true) {
//                // 获取指定数量的数据
//                Message message = connector.getWithoutAck(1);
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId > 0 && size != 0) {
//                    handleDATAChange(message.getEntries());
//                }
//                // 提交确认
//                connector.ack(batchId);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            connector.disconnect();
//            //防止频繁访问数据库链接: 线程睡眠 10秒
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void handleDATAChange(List<CanalEntry.Entry> entrys) {
//        for (CanalEntry.Entry entry : entrys) {
//            // 只解析mysql事务的操作，其他的不解析
//            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
//                continue;
//            }
//            //RowChange对象，包含了一行数据变化的所有特征
//            CanalEntry.RowChange rowChange;
//            try {
//                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
//            }
//            CanalEntry.EventType eventType = rowChange.getEventType();
//            // 获取当前操作所属的数据库
//            String dbName = entry.getHeader().getSchemaName();
//            // 获取当前操作所属的表
//            String tableName = entry.getHeader().getTableName();
//
//            // 事务提交时间
//            long timestamp = entry.getHeader().getExecuteTime();
//
//            log.info("Canal监测到更新:【{}】库的【{}】表", dbName, tableName);
//
//            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
//                dataDetails(rowData.getBeforeColumnsList(), rowData.getAfterColumnsList(), dbName, tableName, eventType, timestamp);
//                log.info("-------------------------------------------------------------");
//            }
//
//        }
//    }
//
//    /**
//     * 解析具体一条Binlog消息的数据
//     *
//     * @param dbName    当前操作所属数据库名称
//     * @param tableName 当前操作所属表名称
//     * @param eventType 当前操作类型（新增、修改、删除）
//     */
//    private static void dataDetails(List<CanalEntry.Column> beforeColumns,
//                                    List<CanalEntry.Column> afterColumns,
//                                    String dbName,
//                                    String tableName,
//                                    CanalEntry.EventType eventType,
//                                    long timestamp) {
//
//        log.info("数据库：{},表名:{},操作类型:{}", dbName, tableName, eventType);
//
//        if (CanalEntry.EventType.INSERT.equals(eventType)) {
//            log.info("新增数据：");
//            printColumn(afterColumns);
//        } else if (CanalEntry.EventType.DELETE.equals(eventType)) {
//            log.info("删除数据：");
//            printColumn(beforeColumns);
//        } else {
//            log.info("更新数据：更新前数据--");
//            printColumn(beforeColumns);
//            log.info("更新数据：更新后数据--");
//            printColumn(afterColumns);
//        }
//        log.info("操作时间：{}", new Date(timestamp));
//    }
//
//    private static void printColumn(List<CanalEntry.Column> columns) {
//        for (CanalEntry.Column column : columns) {
//            log.info(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//}
